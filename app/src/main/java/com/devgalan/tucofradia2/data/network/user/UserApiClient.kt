package com.devgalan.tucofradia2.data.network.user

import com.devgalan.tucofradia2.data.ApiResponse
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.dto.UpdateUserDto
import com.devgalan.tucofradia2.data.model.user.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiClient {
    @GET("users/random")
    suspend fun getRandomUsers(@Query("limit") amount:String): Response<List<User>>
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Long): Response<User>
    @POST("users/register")
    suspend fun registerUser(@Body registerUserDto: RegisterUserDto): Response<ApiResponse<User>>
    @POST("users/login")
    suspend fun loginUser(@Body loginUserDto: LoginUserDto): Response<User>
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") userId: Long, @Body updateUserDto: UpdateUserDto): Response<User>
}