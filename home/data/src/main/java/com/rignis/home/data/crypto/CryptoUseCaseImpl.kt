package com.rignis.home.data.crypto

import com.rignis.home.domain.CryptoUseCase
import javax.inject.Inject

class CryptoUseCaseImpl @Inject constructor() : CryptoUseCase {
    private val cryptoManager = CryptoManager()

    override suspend fun encrypt(message: ByteArray): ByteArray {
        return cryptoManager.encrypt(message)
    }

    override suspend fun decrypt(message: ByteArray): ByteArray {
        return cryptoManager.decrypt(message)
    }
}