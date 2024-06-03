package com.devgalan.tucofradia2.ui.serverList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.domain.server.GetServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val serverProvider: ServerProvider,
    private val getServersUseCase: GetServersUseCase
) : ViewModel() {

    private var serverList: List<Server> = serverProvider.servers

    fun onCreate() {
        viewModelScope.launch {
            getServersUseCase(ResultActions({
                serverList = it
            }, {
                println("Error getting servers: $it")
            }))
        }
    }

    fun filterServerList(name: String, code: String, public: Boolean, full: Boolean): List<Server> {
        return serverList.filter { it.name.contains(name, ignoreCase = true) && it.code.contains(code, ignoreCase = true) && it.public == public && (it.amountPlayers < it.maxPlayers || full) }
    }

    fun joinServer(server: Server) {
        println(server.toString())
    }
}