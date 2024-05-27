package com.devgalan.tucofradia2.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.AntiSpamHelper
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.domain.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(private val antiSpamHelper:AntiSpamHelper, private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    val onError = MutableLiveData<String>()
    val onFinished = MutableLiveData<Boolean>()

    fun registerUser(registerUserDto: RegisterUserDto, confirmPassword: String) {

        antiSpamHelper.checkSpamming().let {
            if (it) {
                onError.value = "No spammees el botón >:l"
                return
            }
        }

        if (!checkIsValidAndGiveError(registerUserDto, confirmPassword)) return

        viewModelScope.launch {
            val user: User = registerUserUseCase(registerUserDto) { onError.postValue(it) }
            onFinished.value = user.id != -1L
        }
    }

    fun checkIsValidAndGiveError(
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