package com.devgalan.tucofradia2.data.network

import com.devgalan.tucofradia2.data.ResultActions

abstract class ApiService {
    protected fun <T> doResultActions(
        response: retrofit2.Response<T>,
        resultActions: ResultActions<T>,
        defaultValue: T
    ): T {
        if (response.isSuccessful) {
            val data = response.body() ?: defaultValue
            resultActions.onSuccess(data)
            return data
        } else {
            resultActions.onError(response.code().toString())
            return defaultValue
        }
    }
}