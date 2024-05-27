package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.repository.user.UserRepository

class RegisterUserUseCase(val onError: (String) -> Unit) {

    private val repository = UserRepository()



    suspend operator fun invoke(registerUserDto: RegisterUserDto) = repository.registerUser(registerUserDto, onError)

}