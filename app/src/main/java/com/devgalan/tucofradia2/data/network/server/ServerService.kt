package com.devgalan.tucofradia2.data.network.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ServerService @Inject constructor(private val api: ServerApiClient) : ApiService() {

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
}