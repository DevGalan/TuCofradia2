package com.devgalan.tucofradia2.data.network.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServerService @Inject constructor(private val api: ServerApiClient) : ApiService() {

    private val ERROR_SERVER = Server(-1, "", "", "", -1, -1, -1, false, User(-1, "", "", "", ""))

    suspend fun getServers(resultActions: ResultActions<List<Server>>): List<Server> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getServers()
                doResultActions(response, resultActions, emptyList())
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
                emptyList()
            }
        }
    }

    suspend fun getMyServers(
        userId: Long,
        resultActions: ResultActions<List<Server>>
    ): List<Server> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMyServers(userId)
                doResultActions(response, resultActions, emptyList())
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
                emptyList()
            }
        }
    }

    suspend fun updateServer(
        id: Long,
        updateServerDto: UpdateServerDto,
        resultActions: ResultActions<Server>
    ): Server {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.updateServer(id, updateServerDto)
                doResultActions(response, resultActions, ERROR_SERVER)
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
                ERROR_SERVER
            }
        }
    }

    suspend fun joinServer(
        joinServerDto: JoinServerDto,
        resultActions: ResultActions<Server>
    ): Server {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.joinServer(joinServerDto)
                doResultActions(response, resultActions, ERROR_SERVER)
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
                ERROR_SERVER
            }
        }
    }

    suspend fun leaveServer(serverId: Long, userId: Long, resultActions: ResultActions<Unit>) {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.leaveServer(serverId, userId)
                doResultActions(response, resultActions, Unit)
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
            }
        }
    }

    suspend fun createServer(createServerDto: CreateServerDto, resultActions: ResultActions<Server>): Server {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.createServer(createServerDto)
                doResultActions(response, resultActions, ERROR_SERVER)
            } catch (e: Exception) {
                resultActions.onError(e.message ?: "Error desconocido")
                ERROR_SERVER
            }
        }
    }
}