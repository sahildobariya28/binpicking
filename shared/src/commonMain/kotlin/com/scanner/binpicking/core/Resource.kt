package com.scanner.binpicking.core

import io.ktor.http.HttpStatusCode

sealed class Resource<T>(val data: T? = null, val status: HttpStatusCode) {

    class Success<T>(data: T, status: HttpStatusCode) : Resource<T>(data, status)

    class Error<T>(data: T? = null, status: HttpStatusCode) : Resource<T>(data, status)

    class Loading<T>(data: T? = null, status: HttpStatusCode = HttpStatusCode.Processing) : Resource<T>(data, status)
}