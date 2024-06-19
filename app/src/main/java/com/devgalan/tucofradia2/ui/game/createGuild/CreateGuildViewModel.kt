package com.devgalan.tucofradia2.ui.game.createGuild

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.guild.CreateGuildDto
import com.devgalan.tucofradia2.data.model.guild.Guild
import com.devgalan.tucofradia2.data.model.guild.GuildProvider
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.domain.guild.CreateGuildUseCase
import com.devgalan.tucofradia2.domain.guild.GetServerGuildsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGuildViewModel @Inject constructor(
    private val createGuildUseCase: CreateGuildUseCase,
    private val getServerGuildsUseCase: GetServerGuildsUseCase,
    private val userProvider: UserProvider,
    private val guildProvider: GuildProvider,
    private val serverProvider: ServerProvider
) : ViewModel() {

    val onFinished = MutableLiveData<Boolean>()

    fun onCreate() {
        guildProvider.myGuild = Guild(-1, "", "", 0, 0, 0, 0, User(-1, "", "", "", ""))
    }

    fun createGuild(createGuildDto: CreateGuildDto, resultActions: ResultActions<Guild>) {
        viewModelScope.launch {
            createGuildUseCase(
                serverProvider.playingServer?.id ?: -1,
                userProvider.currentUser?.id ?: -1,
                createGuildDto,
                resultActions
            )
        }
    }

    fun getServerGuilds() {
        viewModelScope.launch {
            getServerGuildsUseCase(serverProvider.playingServer?.id ?: -1, ResultActions({
                val userId = userProvider.currentUser?.id ?: -1
                it.forEach { guild ->
                    if (guild.user.id == userId) {
                        guildProvider.myGuild = guild
                        return@forEach
                    }
                }
                if (guildProvider.myGuild.id != -1L) {
                    guildProvider.serverGuilds = it.filter { it.id != guildProvider.myGuild.id }
                    onFinished.postValue(true)
                } else {
                    guildProvider.serverGuilds = it
                    onFinished.postValue(false)
                }
                guildProvider.serverGuilds = it
            }, {
                onFinished.postValue(false)
            })
            )
        }
    }
}