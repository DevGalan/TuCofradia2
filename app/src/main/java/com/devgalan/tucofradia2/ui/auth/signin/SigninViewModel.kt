package com.devgalan.tucofradia2.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.help.AntiSpamHelper
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.user.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val antiSpamHelper: AntiSpamHelper,
    private val registerUserUseCase: RegisterUserUseCase,
    private val storageDataAccess: StorageDataAccess
) : ViewModel() {

    val onError = MutableLiveData<String>()
    val onFinished = MutableLiveData<Boolean>()

    fun registerUser(registerUserDto: RegisterUserDto, confirmPassword: String, remember: Boolean) {

        antiSpamHelper.checkSpamming().let {
            if (it) {
                onError.value = "No spammees el botón >:l"
                return
            }
        }

        if (!checkIsValidAndGiveError(registerUserDto, confirmPassword)) return

        viewModelScope.launch {
            registerUserUseCase(registerUserDto, ResultActions({
                handleSigninCall(it, remember, registerUserDto)
            }, {
                onError.postValue(it)
            }))
        }
    }

    private fun handleSigninCall(user: User, remember: Boolean, registerUserDto: RegisterUserDto) {
        val hasUser = user.id != -1L
        if (hasUser) {
            if (remember) {
                storageDataAccess.saveUser(user)
                storageDataAccess.savePassword(registerUserDto.password)
            } else
                storageDataAccess.removeUser()
        }
        onFinished.postValue(hasUser)
    }

    private fun checkIsValidAndGiveError(
        registerUserDto: RegisterUserDto,
        confirmPassword: String
    ): Boolean {
        if (registerUserDto.username.isEmpty() || registerUserDto.email.isEmpty() || registerUserDto.password.isEmpty() || confirmPassword.isEmpty()) {
            onError.value = "Todos los campos son obligatorios."
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(registerUserDto.email).matches()) {
            onError.value = "El email no es válido."
            return false
        }

        if (registerUserDto.password != confirmPassword) {
            onError.value = "Las contraseñas no coinciden."
            return false
        }

        if (registerUserDto.password.length < 8) {
            onError.value = "La contraseña debe tener un mínimo de 8 carácteres."
            return false
        }
        return true
    }
}