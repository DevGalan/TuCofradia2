package com.devgalan.tucofradia2.domain

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.repository.user.UserRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UpdateUserImageUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(
        userId: Long,
        image: MultipartBody.Part,
        resultActions: ResultActions<User>
    ) = repository.updateUserImage(userId, image, resultActions)
}