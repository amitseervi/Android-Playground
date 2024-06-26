package com.blue.fire.data.authentication.firebase

import com.blue.fire.authentication.model.User
import com.blue.fire.authentication.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthRepository : AuthRepository {
    private val auth by lazy {
        Firebase.auth
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String): User {
        return suspendCoroutine<User> { cont ->
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                mapFirebaseUserToModel(it.user)?.let { mappedUser ->
                    cont.resumeWith(Result.success(mappedUser))
                } ?: run {
                    cont.resumeWith(Result.failure(RuntimeException("User is null")))
                }
            }.addOnFailureListener { e ->
                cont.resumeWith(Result.failure(e))
            }
        }
    }

    override suspend fun signupWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ) {
        suspendCoroutine<Unit> { cont ->
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                it.user.let { firebaseUser ->
                    if (firebaseUser != null) {
                        firebaseUser.updateProfile(
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()
                        ).addOnSuccessListener {
                            cont.resumeWith(Result.success(Unit))
                        }.addOnFailureListener { e ->
                            cont.resumeWithException(e)
                        }
                    } else {
                        cont.resumeWithException(RuntimeException("User is null"))
                    }

                }
            }.addOnFailureListener { e ->
                cont.resumeWith(Result.failure(e))
            }
        }
    }
}