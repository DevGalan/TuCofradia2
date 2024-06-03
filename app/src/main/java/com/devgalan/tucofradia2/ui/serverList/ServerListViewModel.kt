package com.devgalan.tucofradia2.ui.serverList

import androidx.lifecycle.MutableLiveData
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

    private var serverList: MutableLiveData<List<Server>> = MutableLiveData()

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

    fun filterServerList(name: String, code: String, public: Boolean, full: Boolean): List<Server> {
        return serverList.value?.filter {
            it.name.contains(name, ignoreCase = true) && it.code.contains(
                code,
                ignoreCase = true
            ) && it.public == public && (it.amountPlayers < it.maxPlayers || full)
        } ?: emptyList()
    }

    fun joinServer(server: Server) {
        println(server.toString())
    }
}