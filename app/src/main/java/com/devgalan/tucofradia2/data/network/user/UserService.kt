package com.devgalan.tucofradia2.data.network.user

import com.devgalan.tucofradia2.data.ApiResponse
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.dto.UpdateUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.network.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApiClient) : ApiService() {

    private val ERROR_USER = User(-1, "", "", "", "")

    suspend fun getRandomUsers(amount: Int, resultActions: ResultActions<List<User>>): List<User> {
        return withContext(Dispatchers.IO) {
            val response =
                api.getRandomUsers(amount.toString())

            doResultActions(response, resultActions, emptyList())
        }
    }

    suspend fun registerUser(
        registerUserDto: RegisterUserDto,
        resultActions: ResultActions<User>
    ): User {
        return withContext(Dispatchers.IO) {
            val response = api.registerUser(registerUserDto)

            if (response.isSuccessful) {
                val user = response.body()?.data ?: ERROR_USER
                resultActions.onSuccess(user)
                user
            } else {
                val gson = Gson()
                val type = object : TypeToken<ApiResponse<User>>() {}.type
                val errorResponse: ApiResponse<User>? =
                    gson.fromJson(response.errorBody()?.string(), type)
                val errorMessage =
                    errorResponse?.message ?: "Error desconocido, código: ${response.code()}"
                resultActions.onError(errorMessage)
                ERROR_USER
            }
        }
    }

    suspend fun loginUser(loginUserDto: LoginUserDto, resultActions: ResultActions<User>): User {
        return withContext(Dispatchers.IO) {
            val response = api.loginUser(loginUserDto)

            if (response.isSuccessful) {
                val user = response.body() ?: ERROR_USER
                resultActions.onSuccess(user)
                user
            } else {
                if (response.code() == 404) {
                    resultActions.onError("Email o contraseñas incorrectos")
                } else {
                    resultActions.onError(response.code().toString())
                }
                ERROR_USER
            }
        }
    }

    suspend fun getUserById(userId: Long, resultActions: ResultActions<User>): User {
        return withContext(Dispatchers.IO) {
            val response = api.getUserById(userId)

            doResultActions(response, resultActions, ERROR_USER)
        }
    }

    suspend fun updateUser(
        userId: Long,
        user: UpdateUserDto,
        resultActions: ResultActions<User>
    ): User {
        return withContext(Dispatchers.IO) {
            val response = api.updateUser(userId, user)

            doResultActions(response, resultActions, ERROR_USER)
        }
    }

    suspend fun updateUserImage(
        userId: Long,
        image: MultipartBody.Part,
        resultActions: ResultActions<User>
    ) {
        return withContext(Dispatchers.IO) {
            val response = api.updateUserImage(userId, image)

            if (response.isSuccessful) {
                val user = response.body() ?: ERROR_USER
                resultActions.onSuccess(user)
            } else {
                resultActions.onError(response.code().toString())
            }
        }
    }
}