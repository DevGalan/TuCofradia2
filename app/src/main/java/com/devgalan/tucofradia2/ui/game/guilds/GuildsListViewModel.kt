package com.devgalan.tucofradia2.ui.game.guilds

import androidx.lifecycle.ViewModel
import com.devgalan.tucofradia2.data.model.guild.GuildProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuildsListViewModel @Inject constructor(
    private val guildProvider: GuildProvider
): ViewModel() {
    fun getGuilds() = guildProvider.serverGuilds
}