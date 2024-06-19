package com.devgalan.tucofradia2.data.model.guild

import com.devgalan.tucofradia2.data.model.user.User

class Guild(
    val id: Long,
    val name: String,
    val description: String,
    val reputation: Int,
    val money: Long,
    val brothers: Int,
    val fee: Byte,
    val user: User
)