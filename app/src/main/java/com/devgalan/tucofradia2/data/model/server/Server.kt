package com.devgalan.tucofradia2.data.model.server

import com.devgalan.tucofradia2.data.model.user.User

data class Server(
    val id: Long,
    val name: String,
    val code: String,
    var description: String,
    var gameMonth: Byte,
    var amountPlayers: Byte,
    var maxPlayers: Byte,
    var public: Boolean,
    val admin: User
) {
    fun isFull(): Boolean {
        return amountPlayers >= maxPlayers
    }
}