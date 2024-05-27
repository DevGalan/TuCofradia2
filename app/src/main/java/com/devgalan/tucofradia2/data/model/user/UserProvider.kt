package com.devgalan.tucofradia2.data.model.user

class UserProvider {
    companion object {
        var randomUsers:List<User> = emptyList()
        var currentUser:User? = null
    }
}