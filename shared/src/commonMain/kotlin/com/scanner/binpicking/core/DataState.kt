package com.scanner.binpicking.core

import io.ktor.http.HttpStatusCode



data class DataState(
    val state: DataConfiguration = DataConfiguration.onIdle,
    val errorResponse: Any? = null,
    val successResponse: Any? = null,
    val responseStatus: HttpStatusCode = HttpStatusCode.Processing
)

sealed class DataConfiguration {
    data object OnIdle : DataConfiguration()
    data object OnLoading : DataConfiguration()
    data object OnSuccess : DataConfiguration()
    data object OnError : DataConfiguration()

    companion object {
        val onIdle: DataConfiguration = OnIdle
    }
}
