package com.devgalan.tucofradia2.ui.profile

import androidx.lifecycle.ViewModel
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.GetRandomUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProvider: UserProvider,
    private val storageDataAccess: StorageDataAccess
) : ViewModel() {

    fun getUser() = userProvider.currentUser

    fun logout() {
        userProvider.currentUser = null
        storageDataAccess.removeUser()
    }

    fun userIsLogged() : Boolean {
        return userProvider.currentUser != null && userProvider.currentUser?.id != -1L
    }
}