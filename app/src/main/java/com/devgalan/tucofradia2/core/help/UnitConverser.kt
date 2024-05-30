package com.devgalan.tucofradia2.core.help

import android.content.Context
import android.view.View

class UnitConverser {
    companion object {
        fun dpToPx(dp: Int, view: View): Int {
            val density = view.context.resources.displayMetrics.density
            return (dp * density).toInt()
        }
    }
}