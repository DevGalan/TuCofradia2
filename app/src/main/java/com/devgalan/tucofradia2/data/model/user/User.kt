package com.devgalan.tucofradia2.data.model.user

data class User(
    val id: Long,
    var username: String,
    val email: String,
    var profileMessage: String,
    val profilePicture: String
)