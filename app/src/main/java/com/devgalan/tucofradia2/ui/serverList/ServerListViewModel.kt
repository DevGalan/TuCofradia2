package com.devgalan.tucofradia2.ui.serverList

import androidx.lifecycle.ViewModel
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.domain.server.GetServerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val serverProvider: ServerProvider,
    private val getServerListUserCase: GetServerListUseCase
) : ViewModel() {

    private var serverList: MutableList<Server> = serverProvider.servers.toMutableList()

    fun onCreate() {
//        getServerListUserCase()
        serverList = serverProvider.servers.toMutableList()
    }

    fun getServerList() = serverList

    fun filterServerList(name: String, code: String, public: Boolean, full: Boolean): List<Server> {
        return serverList.filter { it.name.contains(name, ignoreCase = true) && it.code.contains(code, ignoreCase = true) && it.public == public && (it.amountPlayers < it.maxPlayers || full) }
    }
}