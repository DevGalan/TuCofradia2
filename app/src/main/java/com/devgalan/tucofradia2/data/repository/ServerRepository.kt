package com.devgalan.tucofradia2.data.repository

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.data.network.server.ServerService
import javax.inject.Inject

class ServerRepository  @Inject constructor(
    private val api: ServerService,
    private val serverProvider: ServerProvider
) {
    suspend fun getServers(resultActions: ResultActions<List<Server>>) {
        val response = api.getServers(resultActions)
        serverProvider.servers = (response)
    }

    suspend fun updateServer(id: Long, updateServerDto: UpdateServerDto, resultActions: ResultActions<Server>) {
        api.updateServer(id, updateServerDto, resultActions)
    }

    suspend fun joinServer(joinServerDto: JoinServerDto, resultActions: ResultActions<Server>) {
        val response = api.joinServer(joinServerDto, resultActions)
        serverProvider.playingServer = (response)
    }
}