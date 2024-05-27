package com.devgalan.tucofradia2.core

import android.os.SystemClock

class AntiSpamHelper {
    private var lastRegisterClickTime: Long = 0

    fun checkSpamming(): Boolean {
        if (SystemClock.elapsedRealtime() - lastRegisterClickTime < 1000) {
            return true
        }
        lastRegisterClickTime = SystemClock.elapsedRealtime()
        return false
    }
}