package com.devgalan.tucofradia2.domain.guild

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.guild.Guild
import com.devgalan.tucofradia2.data.repository.ServerRepository
import javax.inject.Inject

class GetServerGuildsUseCase @Inject constructor(private val repository: ServerRepository) {
    suspend operator fun invoke(serverId: Long, resultActions: ResultActions<List<Guild>>) = repository.getServerGuilds(serverId, resultActions)
}