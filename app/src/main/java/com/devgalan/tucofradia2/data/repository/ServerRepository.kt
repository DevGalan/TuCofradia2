package com.devgalan.tucofradia2.data.repository

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.guild.CreateGuildDto
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.guild.Guild
import com.devgalan.tucofradia2.data.model.guild.GuildProvider
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.data.network.server.ServerService
import javax.inject.Inject

class ServerRepository  @Inject constructor(
    private val api: ServerService,
    private val serverProvider: ServerProvider,
    private val guildProvider: GuildProvider
) {
    suspend fun getServers(resultActions: ResultActions<List<Server>>) {
        val response = api.getServers(resultActions)
        serverProvider.servers = (response)
    }

    suspend fun getMyServers(userId: Long, resultActions: ResultActions<List<Server>>) {
        val response = api.getMyServers(userId, resultActions)
        serverProvider.myServers = (response)
    }

    suspend fun updateServer(id: Long, updateServerDto: UpdateServerDto, resultActions: ResultActions<Server>) {
        api.updateServer(id, updateServerDto, resultActions)
    }

    suspend fun joinServer(joinServerDto: JoinServerDto, resultActions: ResultActions<Server>) {
        val response = api.joinServer(joinServerDto, resultActions)
        serverProvider.playingServer = (response)
    }

    suspend fun leaveServer(serverId: Long, userId: Long, resultActions: ResultActions<Unit>) {
        api.leaveServer(serverId, userId, resultActions)
    }

    suspend fun createServer(createServerDto: CreateServerDto, resultActions: ResultActions<Server>) {
        val response = api.createServer(createServerDto, resultActions)
        serverProvider.playingServer = (response)
    }

    suspend fun createGuild(serverId: Long, userId: Long, createGuildDto: CreateGuildDto, resultActions: ResultActions<Guild>) {
        val response = api.createGuild(serverId, userId, createGuildDto, resultActions)
        guildProvider.myGuild = (response)
    }

    suspend fun getServerGuilds(serverId: Long, resultActions: ResultActions<List<Guild>>) {
        val response = api.getGuilds(serverId, resultActions)
        guildProvider.serverGuilds = (response)
    }
}