package com.devgalan.tucofradia2.domain.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.UserRepository
import javax.inject.Inject

class GetRandomUsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(amount: Int, resultActions: ResultActions<List<User>>) =
        repository.getRandomUsers(amount, resultActions)
}