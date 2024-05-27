package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.repository.user.UserRepository

class GetRandomUsersUseCase(val onError: (String) -> Unit) {

    private val repository = UserRepository()

    suspend operator fun invoke(amount: Int) = repository.getRandomUsers(amount, onError)
}