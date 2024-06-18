package com.devgalan.tucofradia2.ui.main.serverList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.domain.server.GetServersUseCase
import com.devgalan.tucofradia2.domain.server.JoinServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val serverProvider: ServerProvider,
    private val getServersUseCase: GetServersUseCase,
    private val joinServerUseCase: JoinServerUseCase
) : ViewModel() {

    private var serverList: MutableLiveData<List<Server>> = MutableLiveData()

    fun getServerList() = serverList

    fun onCreate() {
        serverList.value = serverProvider.servers
        viewModelScope.launch {
            getServersUseCase(ResultActions({
                serverList.postValue(it)
                serverProvider.servers = it
            }, {
                println("Error getting servers: $it")
            }))
        }
    }

    fun setJoinedServer(server: Server) {
        serverProvider.playingServer = server
    }

    fun filterServerList(name: String, code: String, public: Boolean, full: Boolean): List<Server> {
        return serverList.value?.filter {
            it.name.contains(name, ignoreCase = true) && it.code.contains(
                code,
                ignoreCase = true
            ) && it.isPublic == public && (it.amountPlayers < it.maxPlayers || full)
        } ?: emptyList()
    }

    fun joinServer(serverCode: String, serverPassword: String, resultActions: ResultActions<Server>) {
        viewModelScope.launch {
            joinServerUseCase(
                JoinServerDto(serverCode, serverPassword),
                resultActions
            )
        }
    }
}