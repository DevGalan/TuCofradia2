package com.devgalan.tucofradia2.ui.profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgalan.tucofradia2.core.help.ImageHelper
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.user.UpdateUserDto
import com.devgalan.tucofradia2.data.model.user.User
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.user.UpdateUserImageUseCase
import com.devgalan.tucofradia2.domain.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProvider: UserProvider,
    private val storageDataAccess: StorageDataAccess,
    private val updateUserUseCase: UpdateUserUseCase,
    private val updateUserImageUseCase: UpdateUserImageUseCase
) : ViewModel() {

    val onFinished = MutableLiveData<Boolean>()

    fun getUser() = userProvider.currentUser

    fun logout() {
        userProvider.currentUser = null
        storageDataAccess.removeUser()
    }

    fun userIsLogged(): Boolean {
        return userProvider.currentUser != null && userProvider.currentUser?.id != -1L
    }

    fun updateProfile(username: String, message: String) {
        val currentUser = userProvider.currentUser
        val userId = currentUser?.id ?: -1
        val updateUserDto = UpdateUserDto(
            username.ifEmpty { currentUser?.username ?: "" },
            message.ifEmpty { currentUser?.profileMessage ?: "" }
        )
        viewModelScope.launch {
            updateUserUseCase(userId, updateUserDto, ResultActions({
                handleUpdateUserCall(it)
                onFinished.postValue(true)
                println("User updated: $it")
            }, {
                println("Error updating user: $it")
                onFinished.postValue(false)
            }))
        }
    }

    private fun handleUpdateUserCall(user: User) {
        userProvider.currentUser = user;
    }

    fun uploadProfileImage(context: Context, uri: Uri) {
        val currentUser = userProvider.currentUser
        val userId = currentUser?.id ?: -1
        val imageHelper = ImageHelper()
        val multipartImage = imageHelper.getMultipartFromFile(imageHelper.getFileFromUri(context, uri), "image")
        viewModelScope.launch {
            updateUserImageUseCase(userId, multipartImage, ResultActions({
                handleUpdateUserCall(it)
                Thread.sleep(1000)
                onFinished.postValue(true)
            }, {
                println("Error updating user: $it")
                onFinished.postValue(false)
            }))
        }
    }
}