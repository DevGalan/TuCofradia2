package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class GetRandomUsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(amount: Int, onError: (String) -> Unit) = repository.getRandomUsers(amount, onError)
}