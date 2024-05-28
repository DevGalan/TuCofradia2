package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(userId: Long, onError: (String) -> Unit) =
        repository.getUserById(userId, onError)

}