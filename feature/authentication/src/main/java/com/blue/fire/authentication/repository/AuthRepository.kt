package com.blue.fire.authentication.repository

import androidx.lifecycle.LiveData
import com.blue.fire.authentication.model.User

interface AuthRepository {
    val currentUser: LiveData<User>
    suspend fun loginWithEmailAndPassword(email: String, password: String): User?

    suspend fun signupWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    )
}