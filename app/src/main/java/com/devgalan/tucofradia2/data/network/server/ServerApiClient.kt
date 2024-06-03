package com.devgalan.tucofradia2.data.network.server

import com.devgalan.tucofradia2.data.model.server.Server
import retrofit2.Response
import retrofit2.http.GET

interface ServerApiClient {
    @GET("servers")
    suspend fun getServers(): Response<List<Server>>
}