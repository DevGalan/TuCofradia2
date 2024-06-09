package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class UpdateServersUseCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(id: Long, updateServerDto: UpdateServerDto, resultActions: ResultActions<Server>) =
        repository.updateServer(id, updateServerDto, resultActions)
}