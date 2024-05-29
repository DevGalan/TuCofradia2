package com.devgalan.tucofradia2.ui.auth.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.AntiSpamHelper
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.GetRandomUsersUseCase
import com.devgalan.tucofradia2.domain.LoginUserUseCase
import com.devgalan.tucofradia2.domain.RegisterUserUseCase
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
            val user: User = loginUserUseCase(loginUserDto) { onError.postValue(it) }
            val hasUser = user.id != -1L
            if (hasUser) {
                if (remember) {
                    storageDataAccess.saveUser(user)
                    storageDataAccess.savePassword(loginUserDto.password)
                }
                else
                    storageDataAccess.removeUser()
            }
            onFinished.value = hasUser
        }
    }

    fun checkIsValidAndGiveError(
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