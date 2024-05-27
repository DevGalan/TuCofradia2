package com.devgalan.tucofradia2.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.domain.GetRandomUsersUseCase
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val getRandomUsersUseCase = GetRandomUsersUseCase({ println(it) })

    fun onCreate() {
        viewModelScope.launch {
            val users = getRandomUsersUseCase(3)
            users.forEach {
                println(it)
            }
        }
    }
}