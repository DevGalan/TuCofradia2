package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.repository.user.UserRepository

class LoginUserUseCase(val onError: (String) -> Unit) {

    private val repository = UserRepository()

    suspend operator fun invoke(loginUserDto: LoginUserDto) =
        repository.loginUser(loginUserDto, onError)
}