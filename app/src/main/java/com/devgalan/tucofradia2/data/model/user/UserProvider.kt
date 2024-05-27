package com.devgalan.tucofradia2.data.model.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProvider @Inject constructor() {
    var randomUsers:List<User> = emptyList()
    var currentUser:User? = null
}