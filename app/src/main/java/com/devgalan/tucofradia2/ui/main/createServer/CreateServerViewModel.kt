package com.devgalan.tucofradia2.ui.main.createServer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.help.AntiSpamHelper
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.data.model.server.ServerProvider
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.domain.server.CreateServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateServerViewModel @Inject constructor(
    private val antiSpamHelper: AntiSpamHelper,
    private val createServerUseCase: CreateServerUseCase,
    private val serverProvider: ServerProvider,
    private val userProvider: UserProvider
) : ViewModel() {

    val onError = MutableLiveData<String>()
    val onFinished = MutableLiveData<Boolean>()

    fun getUserId(): Long {
        return userProvider.currentUser?.id ?: -1L
    }

    fun createServer(createServerDto: CreateServerDto) {
        antiSpamHelper.checkSpamming().let {
            if (it) {
                onError.value = "No spammees el botón >:l"
                return
            }
        }

        if (!checkIsValidAndGiveError(createServerDto)) return

        viewModelScope.launch {
            createServerUseCase(createServerDto, ResultActions({
                handleCreationCall(it)
            }, {
                onError.postValue(it)
            }))
        }
    }

    private fun checkIsValidAndGiveError(
        createServerDto: CreateServerDto
    ): Boolean {
        if (createServerDto.name.isEmpty() || createServerDto.maxPlayers == null) {
            onError.value = "Debes rellenar todos los campos obligatorios."
            return false
        }
        return true
    }

    private fun handleCreationCall(
        server: Server
    ) {
        if (server.id == -1L) {
            onError.postValue("Error al crear el servidor.")
            onFinished.postValue(false)
            return
        }
        serverProvider.playingServer = server
        onFinished.postValue(true)
    }
}