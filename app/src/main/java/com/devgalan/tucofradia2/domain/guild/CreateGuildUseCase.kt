package com.devgalan.tucofradia2.domain.guild

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.guild.CreateGuildDto
import com.devgalan.tucofradia2.data.model.guild.Guild
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class CreateGuildUseCase @Inject constructor(private val repository: ServerRepository) {
        suspend operator fun invoke(serverId: Long, userId: Long, createGuildDto: CreateGuildDto, resultActions: ResultActions<Guild>) = repository.createGuild(serverId, userId, createGuildDto, resultActions)
}