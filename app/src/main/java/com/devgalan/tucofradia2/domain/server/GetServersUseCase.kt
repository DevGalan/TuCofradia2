package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class GetServersUseCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(resultActions: ResultActions<List<Server>>) =
        repository.getServers(resultActions)
}