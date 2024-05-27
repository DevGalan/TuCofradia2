package com.devgalan.tucofradia2.data.repository.user

import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.network.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService, private val userProvider: UserProvider){

    suspend fun getRandomUsers(amount: Int, onError: (String) -> Unit) : List<User> {
        val response = api.getRandomUsers(amount, onError)
        userProvider.randomUsers = response
        return response
    }

    suspend fun registerUser(registerUserDto: RegisterUserDto, onError: (String) -> Unit) : User {
        val response = api.registerUser(registerUserDto, onError)
        userProvider.currentUser = response
        return response
    }

    suspend fun loginUser(loginUserDto: LoginUserDto, onError: (String) -> Unit) : User {
        val response = api.loginUser(loginUserDto, onError)
        userProvider.currentUser = response
        return response
    }
}