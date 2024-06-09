package com.devgalan.tucofradia2.data.network.server

import com.devgalan.tucofradia2.data.dto.UpdateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerApiClient {
    @GET("servers")
    suspend fun getServers(): Response<List<Server>>
    @PUT("servers/{id}/update")
    suspend fun updateServer(@Path("id") id: Long, @Body updateServerDto: UpdateServerDto): Response<Server>
}