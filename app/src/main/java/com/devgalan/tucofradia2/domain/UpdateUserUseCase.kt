package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(user:User, onSuccess: () -> Unit, onError: (String) -> Unit) = repository.updateUser(user, onSuccess, onError)

}