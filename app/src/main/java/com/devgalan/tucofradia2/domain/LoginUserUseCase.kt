package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(loginUserDto: LoginUserDto, onError: (String) -> Unit) =
        repository.loginUser(loginUserDto, onError)
}