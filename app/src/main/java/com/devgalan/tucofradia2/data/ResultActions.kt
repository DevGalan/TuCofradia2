package com.devgalan.tucofradia2.data

data class ResultActions<T>(
    val onSuccess: (resultObject:T) -> Unit,
    val onError: (String) -> Unit
)
