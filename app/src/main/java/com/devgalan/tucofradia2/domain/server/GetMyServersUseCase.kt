package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class GetMyServersUseCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(userId: Long, resultActions: ResultActions<List<Server>>) =
        repository.getMyServers(userId, resultActions)
}