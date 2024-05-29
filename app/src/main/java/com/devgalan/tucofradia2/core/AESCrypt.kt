package com.devgalan.tucofradia2.core

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class AESCrypt {
    private val ALGORITHM = "AES"
    private val KEY = "1Hbfh667adfDEJ78"

    @Throws(Exception::class)
    fun encrypt(value: String): String {
        val key: Key = generateKey()
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedByteValue = cipher.doFinal(value.toByteArray(charset("utf-8")))
        val encryptedValue64: String = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT)
        return encryptedValue64
    }

    @Throws(Exception::class)
    fun decrypt(value: String?): String {
        val key: Key = generateKey()
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedValue64: ByteArray = Base64.decode(value, Base64.DEFAULT)
        val decryptedByteValue = cipher.doFinal(decryptedValue64)
        val decryptedValue = String(decryptedByteValue, charset("utf-8"))
        return decryptedValue
    }

    @Throws(Exception::class)
    private fun generateKey(): Key {
        val key: Key = SecretKeySpec(KEY.toByteArray(), ALGORITHM)
        return key
    }
}