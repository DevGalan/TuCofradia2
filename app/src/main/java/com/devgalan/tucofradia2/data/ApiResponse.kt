package com.devgalan.tucofradia2.data

data class ApiResponse<T>(
    val message: String,
    val data: T
)