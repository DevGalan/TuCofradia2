package com.devgalan.tucofradia2.data.model.server

import com.devgalan.tucofradia2.data.model.user.User

data class Server(
    val id: Long,
    val name: String,
    val code: String,
    val description: String,
    val gameMonth: Byte,
    val amountPlayers: Int,
    val maxPlayers: Int,
    val admin: User
)