/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rignis.authentication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.rignis.authentication.domain.model.User
import com.rignis.authentication.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthRepository
    @Inject
    constructor() : AuthRepository {
        private val auth by lazy {
            Firebase.auth
        }

        private val mFirebaseCurrentUser = MutableLiveData<User>()

        override val currentUser: LiveData<User>
            get() = mFirebaseCurrentUser

        init {
            auth.addAuthStateListener {
                mFirebaseCurrentUser.value = mapFirebaseUserToModel(it.currentUser)
            }
        }

        override suspend fun loginWithEmailAndPassword(
            email: String,
            password: String,
        ): User =
            suspendCoroutine { cont ->
                auth
                    .signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        mapFirebaseUserToModel(it.user)?.let { mappedUser ->
                            cont.resumeWith(Result.success(mappedUser))
                        } ?: run {
                            cont.resumeWith(Result.failure(RuntimeException("User is null")))
                        }
                    }.addOnFailureListener { e ->
                        cont.resumeWith(Result.failure(e))
                    }
            }

        override suspend fun signupWithEmailAndPassword(
            name: String,
            email: String,
            password: String,
        ) {
            suspendCoroutine<Unit> { cont ->
                auth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        it.user.let { firebaseUser ->
                            if (firebaseUser != null) {
                                firebaseUser
                                    .updateProfile(
                                        UserProfileChangeRequest
                                            .Builder()
                                            .setDisplayName(name)
                                            .build(),
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