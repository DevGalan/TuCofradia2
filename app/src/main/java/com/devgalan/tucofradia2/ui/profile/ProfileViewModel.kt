package com.devgalan.tucofradia2.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.domain.GetRandomUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val userProvider: UserProvider
) : ViewModel() {

    fun getUser() = userProvider.currentUser

    fun logout() {
        userProvider.currentUser = null
    }
}