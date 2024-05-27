package com.devgalan.tucofradia2.data.model.user

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val profileMessage: String,
    val profilePicture: String
)