package com.devgalan.tucofradia2.data.repository.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.dto.UpdateUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.network.user.UserService
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService,
    private val userProvider: UserProvider
) {

    suspend fun getRandomUsers(amount: Int, resultActions: ResultActions<List<User>>) {
        val response = api.getRandomUsers(amount, resultActions)
        userProvider.randomUsers = response
    }

    suspend fun registerUser(registerUserDto: RegisterUserDto, resultActions: ResultActions<User>) {
        val response = api.registerUser(registerUserDto, resultActions)
        userProvider.currentUser = response
    }

    suspend fun loginUser(loginUserDto: LoginUserDto, resultActions: ResultActions<User>) {
        val response = api.loginUser(loginUserDto, resultActions)
        userProvider.currentUser = response
    }

    suspend fun getUserById(userId: Long, resultActions: ResultActions<User>) {
        val response = api.getUserById(userId, resultActions)
        userProvider.currentUser = response
    }

    suspend fun updateUser(userId: Long, user: UpdateUserDto, resultActions: ResultActions<User>) {
        val response = api.updateUser(userId, user, resultActions)
        userProvider.currentUser = response
    }

    suspend fun updateUserImage(userId: Long, image: MultipartBody.Part, resultActions: ResultActions<User>) {
        api.updateUserImage(userId, image, resultActions)
    }
}