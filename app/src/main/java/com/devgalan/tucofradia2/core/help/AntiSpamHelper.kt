package com.devgalan.tucofradia2.core.help

import android.os.SystemClock
import javax.inject.Inject

class AntiSpamHelper @Inject constructor() {
    private var lastRegisterClickTime: Long = 0

    fun checkSpamming(): Boolean {
        if (SystemClock.elapsedRealtime() - lastRegisterClickTime < 1000) {
            return true
        }
        lastRegisterClickTime = SystemClock.elapsedRealtime()
        return false
    }
}