package com.devgalan.tucofradia2.data.network.server

import com.devgalan.tucofradia2.data.dto.guild.CreateGuildDto
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.guild.Guild
import com.devgalan.tucofradia2.data.model.server.Server
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerApiClient {
    @GET("servers")
    suspend fun getServers(): Response<List<Server>>
    @GET("servers/user/{userId}")
    suspend fun getMyServers(@Path("userId") userId: Long): Response<List<Server>>
    @PUT("servers/{id}/update")
    suspend fun updateServer(@Path("id") id: Long, @Body updateServerDto: UpdateServerDto): Response<Server>
    @POST("servers/join")
    suspend fun joinServer(@Body joinServerDto: JoinServerDto): Response<Server>
    @DELETE("servers/{serverId}/leave/{userId}")
    suspend fun leaveServer(@Path("serverId") serverId: Long, @Path("userId") userId: Long): Response<Unit>
    @POST("servers/create")
    suspend fun createServer(@Body createServerDto: CreateServerDto): Response<Server>
    @POST("servers/{serverId}/user/{userId}/guild/create")
    suspend fun createGuild(@Path("serverId") serverId: Long, @Path("userId") userId: Long, @Body createGuildDto: CreateGuildDto): Response<Guild>
    @GET("servers/{serverId}/guilds")
    suspend fun getGuilds(@Path("serverId") serverId: Long): Response<List<Guild>>
}