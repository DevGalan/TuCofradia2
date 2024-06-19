package com.devgalan.tucofradia2.data.model.guild

import com.devgalan.tucofradia2.data.model.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GuildProvider @Inject constructor() {
    var serverGuilds: List<Guild> = emptyList()
    var myGuild: Guild = Guild(-1, "", "", 0, 0, 0, 0, User(-1, "", "", "", ""))
}