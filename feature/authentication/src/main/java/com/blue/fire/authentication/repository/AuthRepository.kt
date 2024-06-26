package com.blue.fire.authentication.repository

import com.blue.fire.authentication.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User>
    suspend fun loginWithEmailAndPassword(email: String, password: String): User?

    suspend fun signupWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    )
}