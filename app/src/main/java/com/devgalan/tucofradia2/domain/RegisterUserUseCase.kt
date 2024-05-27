package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(registerUserDto: RegisterUserDto, onError: (String) -> Unit) = repository.registerUser(registerUserDto, onError)

}