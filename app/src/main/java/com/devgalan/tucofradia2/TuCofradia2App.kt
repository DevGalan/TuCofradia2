package com.devgalan.tucofradia2

import android.app.Application
import android.util.Log
import com.devgalan.tucofradia2.data.model.user.UserProvider
import com.devgalan.tucofradia2.data.storage.StorageDataAccess
import com.devgalan.tucofradia2.domain.GetUserByIdUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TuCofradia2App: Application() {

    @Inject
    lateinit var userProvider: UserProvider

    @Inject
    lateinit var storageDataAccess: StorageDataAccess

    @Inject
    lateinit var getUserByIdUseCase: GetUserByIdUseCase

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            getSavedUser()
        }
    }

    suspend fun getSavedUser() {
        val savedUserId = storageDataAccess.getUser().id
        if (savedUserId != -1L) {
            userProvider.currentUser = getUserByIdUseCase(savedUserId) {
                Log.e("TuCofradia2App get saved user", it)
            }
        }
    }
}