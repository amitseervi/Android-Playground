package com.rignis.home.data

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }
    private val encryptionCypher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry("secret", null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    fun encrypt(message: ByteArray): ByteArray {

        println("Encrypt $message")
        val encryptedBytes = encryptionCypher.doFinal(message)
        val outputStream = ByteArrayOutputStream()
        outputStream.use {
            it.write(encryptionCypher.iv.size)
            it.write(encryptionCypher.iv)
            println("Iv array")
            logByteArray(encryptionCypher.iv)
            it.write(encryptedBytes.size)
            it.write(encryptedBytes)
            println("Encrypted message")
            logByteArray(encryptedBytes)
        }
        val result = outputStream.toByteArray()
        logByteArray(result)
        return result
    }

    fun decrypt(fullEncryptedMessage: ByteArray): ByteArray {
        println("Decrypt")
        logByteArray(fullEncryptedMessage)
        val inputStream = ByteArrayInputStream(fullEncryptedMessage)
        return inputStream.use {
            val ivSize = it.read()
            val ivArray = ByteArray(ivSize)
            it.read(ivArray)
            println("IV array")
            logByteArray(ivArray)
            val encryptedSize = it.read()
            val encryptedMessage = ByteArray(encryptedSize)
            it.read(encryptedMessage)
            println("Encrypted message")
            logByteArray(encryptedMessage)
            getDecryptCipherForIv(ivArray).doFinal(encryptedMessage)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun logByteArray(byteArray: ByteArray) {
        print("[")
        byteArray.forEach {
            print(it)
            print(", ")
        }
        println("]")
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}