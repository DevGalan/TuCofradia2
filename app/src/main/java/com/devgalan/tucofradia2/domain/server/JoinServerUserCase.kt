package com.devgalan.tucofradia2.domain.server

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.JoinServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class JoinServerUserCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(
        joinServerDto: JoinServerDto,
        resultActions: ResultActions<Server>
    ) =
        repository.joinServer(joinServerDto, resultActions)
}