package com.devgalan.tucofradia2.domain.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.user.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(registerUserDto: RegisterUserDto, resultActions: ResultActions<User>) = repository.registerUser(registerUserDto, resultActions)

}