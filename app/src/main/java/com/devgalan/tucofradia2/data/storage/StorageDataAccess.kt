package com.devgalan.tucofradia2.data.storage

import com.devgalan.tucofradia2.core.data.StorageData
import com.devgalan.tucofradia2.data.model.user.User
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageDataAccess @Inject constructor(val dataStorage: StorageData, val gson: Gson) {

    fun saveUser(user: User) {
        dataStorage.saveString("LoggedUser", gson.toJson(user))
    }

    fun removeUser() {
        dataStorage.remove("LoggedUser")
    }

    fun getUser(): User {
        val userString = dataStorage.getString("LoggedUser")

        if (userString != "") {
            val user = gson.fromJson(userString, User::class.java)
            if (user != null && user.id != -1L) {
                return user
            }
        }
        return User(-1, "", "", "", "")
    }

    fun getPassword(): String {
        return dataStorage.decryptAndGetString("password") ?: ""
    }

    fun savePassword(password: String) {
        dataStorage.encryptAndSaveString("password", password)
    }
}