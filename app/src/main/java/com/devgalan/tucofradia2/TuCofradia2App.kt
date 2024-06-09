package com.devgalan.tucofradia2

import android.app.Application
import android.util.Log
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.user.LoginUserDto
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.news.GetNewsUseCase
import com.devgalan.tucofradia2.domain.user.LoginUserUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TuCofradia2App : Application() {

    @Inject
    lateinit var storageDataAccess: StorageDataAccess

    @Inject
    lateinit var loginUserUseCase: LoginUserUseCase

    @Inject
    lateinit var getNewsUseCase: GetNewsUseCase

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            try {
                getSavedUser()
                getNews()
            } catch (e: Exception) {
                Log.e("TuCofradia2App", "Error initializing app: $e")
            }
        }
    }

    private suspend fun getSavedUser() {
        val savedUser = storageDataAccess.getUser()
        val password = storageDataAccess.getPassword()
        if (savedUser.id != -1L && password.isNotEmpty()) {
            val loginUserDto = LoginUserDto(savedUser.email, password)
            loginUserUseCase(loginUserDto, ResultActions({
                if (it.id == -1L) {
                    storageDataAccess.removeUser()
                }
            }, {
                Log.e("TuCofradia2App", "Error logging in user: $it")
            }))
        }
    }

    private suspend fun getNews() {
        getNewsUseCase(ResultActions({
            Log.d("TuCofradia2App", "News: $it")

        }, {
            Log.e("TuCofradia2App", "Error getting news: $it")
        }))
    }
}