package com.devgalan.tucofradia2.data.dto.server

class CreateServerDto (
    val name: String,
    val description: String,
    val password: String,
    val maxPlayers: Int?,
    val adminId: Long
)
