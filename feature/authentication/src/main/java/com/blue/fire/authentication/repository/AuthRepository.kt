package com.blue.fire.authentication.repository

import com.blue.fire.authentication.model.User

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): User?

    suspend fun signupWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    )
}