package com.devgalan.tucofradia2.core

import android.content.Context

class Prefs(val context: Context) : StorageData {
    private val PREFS_NAME = "TuCofradia2Prefs"
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    override fun encryptAndSaveString(key: String, value: String) {
        val encryptedValue = AESCrypt().encrypt(value)
        prefs.edit().putString(key, encryptedValue).apply()
    }

    override fun decryptAndGetString(key: String): String? {
        val encryptedValue = prefs.getString(key, null)
        return if (encryptedValue != null) {
            AESCrypt().decrypt(encryptedValue)
        } else {
            null
        }
    }

    override fun saveInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}