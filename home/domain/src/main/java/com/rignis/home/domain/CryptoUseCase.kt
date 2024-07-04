package com.rignis.home.domain

interface CryptoUseCase {
    suspend fun encrypt(message: ByteArray): ByteArray
    suspend fun decrypt(message: ByteArray): ByteArray
}