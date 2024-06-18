package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class CreateServerUseCase @Inject constructor(private val repository: ServerRepository) {

    suspend operator fun invoke(createServerDto: CreateServerDto, resultActions: ResultActions<Server>) = repository.createServer(createServerDto, resultActions)
}