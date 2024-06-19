package com.devgalan.tucofradia2.ui.main.myServers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.UpdateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.domain.server.GetMyServersUseCase
import com.devgalan.tucofradia2.domain.server.LeaveServerUseCase
import com.devgalan.tucofradia2.domain.server.UpdateServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyServersViewModel @Inject constructor(
    private val serverProvider: ServerProvider,
    private val userProvider: UserProvider,
    private val leaveServerUseCase: LeaveServerUseCase,
    private val getMyServersUseCase: GetMyServersUseCase,
    private val updateServersUseCase: UpdateServersUseCase
) : ViewModel() {

    private val myServersList : MutableLiveData<List<Server>> = MutableLiveData()

    fun onCreate() {
        val userId = if (userProvider.currentUser != null) userProvider.currentUser!!.id else -1
        myServersList.postValue(serverProvider.myServers)
        viewModelScope.launch {
            getMyServersUseCase(userId, ResultActions({
                myServersList.postValue(it)
                serverProvider.servers = it
            }, {
                println("Error getting servers: $it")
            }))
        }
    }

    fun getMyServers() = myServersList

    fun leaveServer(serverId: Long, resultActions: ResultActions<Unit>) {
        viewModelScope.launch {
            val userId = if (userProvider.currentUser != null) userProvider.currentUser!!.id else -1
            leaveServerUseCase(serverId, userId, resultActions)
        }
    }

    fun setJoinedServer(server: Server) {
        serverProvider.playingServer = server
    }

    fun editServer(serverId: Long, password: String, description: String, resultActions: ResultActions<Server>) {
        viewModelScope.launch {
            updateServersUseCase(
                serverId,
                UpdateServerDto(password, description),
                resultActions
            )
        }
    }

    fun getUserId(): Long {
        return userProvider.currentUser?.id ?: -1
    }
}