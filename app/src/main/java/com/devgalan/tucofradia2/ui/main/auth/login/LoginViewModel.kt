package com.devgalan.tucofradia2.ui.main.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.help.AntiSpamHelper
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.user.LoginUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.user.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val antiSpamHelper: AntiSpamHelper,
    private val loginUserUseCase: LoginUserUseCase,
    private val storageDataAccess: StorageDataAccess
) : ViewModel() {

    val onError = MutableLiveData<String>()
    val onFinished = MutableLiveData<Boolean>()

    fun loginUser(loginUserDto: LoginUserDto, remember: Boolean) {

        antiSpamHelper.checkSpamming().let {
            if (it) {
                onError.value = "No spammees el botón >:l"
                return
            }
        }

        if (!checkIsValidAndGiveError(loginUserDto)) return

        viewModelScope.launch {
            loginUserUseCase(loginUserDto, ResultActions({
                handleLoginCall(it, remember, loginUserDto)
            }, {
                onError.postValue(it)
            }))
        }
    }

    private fun handleLoginCall(
        user: User,
        remember: Boolean,
        loginUserDto: LoginUserDto
    ) {
        val hasUser = user.id != -1L
        if (hasUser) {
            if (remember) {
                storageDataAccess.saveUser(user)
                storageDataAccess.savePassword(loginUserDto.password)
            } else
                storageDataAccess.removeUser()
        }
        onFinished.postValue(hasUser)
    }

    private fun checkIsValidAndGiveError(
        loginUserDto: LoginUserDto
    ): Boolean {
        if (loginUserDto.email.isEmpty() || loginUserDto.password.isEmpty()) {
            onError.value = "Todos los campos son obligatorios."
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginUserDto.email).matches()) {
            onError.value = "El email no es válido."
            return false
        }
        return true
    }
}