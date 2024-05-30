package com.devgalan.tucofradia2.domain.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(userId: Long, resultActions: ResultActions<User>) =
        repository.getUserById(userId, resultActions)

}