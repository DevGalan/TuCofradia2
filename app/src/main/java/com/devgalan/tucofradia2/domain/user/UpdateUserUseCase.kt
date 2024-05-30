package com.devgalan.tucofradia2.domain.user

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.UpdateUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(userId: Long, user:UpdateUserDto, resultActions: ResultActions<User>) = repository.updateUser(userId, user, resultActions)

}