package com.devgalan.tucofradia2.data.network.user

import com.devgalan.tucofradia2.core.RetrofitHelper
import com.devgalan.tucofradia2.data.ApiResponse
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api:UserApiClient) {

    suspend fun getRandomUsers(amount: Int, onError: (String) -> Unit): List<User> {
        return withContext(Dispatchers.IO) {
            val response =
                api.getRandomUsers(amount.toString())
            if (!response.isSuccessful) {
                onError(response.errorBody()?.string() ?: "Error desconocido")
            }
            response.body() ?: emptyList()
        }
    }

    suspend fun registerUser(registerUserDto: RegisterUserDto, onError: (String) -> Unit): User {
        return withContext(Dispatchers.IO) {
            val response = api.registerUser(registerUserDto)
            if (!response.isSuccessful) {
                val gson = Gson()
                val type = object : TypeToken<ApiResponse<User>>() {}.type
                val errorResponse: ApiResponse<User>? =
                    gson.fromJson(response.errorBody()?.string(), type)
                onError(errorResponse?.message ?: "Error desconocido")
                User(-1, "", "", "", "")
            } else {
                val user = response.body()?.data
                if (user == null) {
                    onError("Error desconocido")
                    User(-1, "", "", "", "")
                } else {
                    user
                }
            }
        }
    }

    suspend fun loginUser(loginUserDto: LoginUserDto, onError: (String) -> Unit): User {
        return withContext(Dispatchers.IO) {
            val response = api.loginUser(loginUserDto)
            if (!response.isSuccessful) {
                onError("Email o contraseñas incorrectos")
                User(-1, "", "", "", "")
            } else {
                response.body() ?: User(-1, "", "", "", "")
            }
        }
    }
}