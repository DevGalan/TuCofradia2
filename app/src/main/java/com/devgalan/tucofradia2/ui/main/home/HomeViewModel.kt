package com.devgalan.tucofradia2.ui.main.home

import androidx.lifecycle.ViewModel
import com.devgalan.tucofradia2.data.model.user.UserProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userProvider: UserProvider
) : ViewModel() {
    fun isUserLogged(): Boolean {
        return userProvider.currentUser != null && userProvider.currentUser!!.id != -1L
    }
}