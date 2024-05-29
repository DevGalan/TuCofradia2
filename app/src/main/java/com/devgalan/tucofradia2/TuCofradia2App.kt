package com.devgalan.tucofradia2

import android.app.Application
import android.util.Log
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.LoginUserUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TuCofradia2App : Application() {

    @Inject
    lateinit var userProvider: UserProvider

    @Inject
    lateinit var storageDataAccess: StorageDataAccess

    @Inject
    lateinit var loginUserUseCase: LoginUserUseCase

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            getSavedUser()
        }
    }

    suspend fun getSavedUser() {
        val savedUser = storageDataAccess.getUser()
        val password = storageDataAccess.getPassword()
        println(savedUser.toString())
        println(password)
        if (savedUser.id != -1L && password.isNotEmpty()) {
            val loginUserDto = LoginUserDto(savedUser.email, password)
            loginUserUseCase(loginUserDto, ResultActions({
                if (it.id != -1L) {
                    userProvider.currentUser = it
                } else {
                    storageDataAccess.removeUser()
                }
            }, {
                Log.e("TuCofradia2App", "Error logging in user: $it")
            }))
        }
    }
}