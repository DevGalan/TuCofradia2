package com.devgalan.tucofradia2.domain.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(loginUserDto: LoginUserDto, resultActions: ResultActions<User>) =
        repository.loginUser(loginUserDto, resultActions)
}