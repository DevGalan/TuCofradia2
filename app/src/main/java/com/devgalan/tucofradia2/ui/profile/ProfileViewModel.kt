package com.devgalan.tucofradia2.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProvider: UserProvider,
    private val storageDataAccess: StorageDataAccess,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    val onFinished = MutableLiveData<Boolean>()

    fun getUser() = userProvider.currentUser

    fun logout() {
        userProvider.currentUser = null
        storageDataAccess.removeUser()
    }

    fun userIsLogged() : Boolean {
        return userProvider.currentUser != null && userProvider.currentUser?.id != -1L
    }

    fun updateProfile(username: String, message: String) {
        val currentUser = userProvider.currentUser
        val modifiedUser = User(currentUser?.id ?: -1, currentUser?.username ?: "", currentUser?.email ?: "", currentUser?.username ?: "", currentUser?.profileMessage ?: "")
        modifiedUser.let {
            it.username = username
            it.profileMessage = message
            userProvider.currentUser = it
            viewModelScope.launch {
                updateUserUseCase(modifiedUser, {userProvider.currentUser = modifiedUser; onFinished.postValue(true)}) { error ->
                    println("Error updating user: $error")
                    onFinished.postValue(false)
                }
            }
        }
    }
}