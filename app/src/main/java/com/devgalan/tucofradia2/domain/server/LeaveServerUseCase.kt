package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class LeaveServerUseCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(serverId: Long, userId: Long, resultActions: ResultActions<Unit>) =
        repository.leaveServer(serverId, userId, resultActions)
}