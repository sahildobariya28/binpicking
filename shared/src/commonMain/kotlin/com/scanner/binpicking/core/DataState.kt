package com.scanner.binpicking.core

import io.ktor.http.HttpStatusCode

data class ListDataState(
    val isLoading: Boolean = false,
    val data: List<Any>? = null,
    val status: HttpStatusCode = HttpStatusCode.Processing
)

data class SingleDataState(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val status: HttpStatusCode = HttpStatusCode.Processing
)

//data class BrandState(
//    val isLoading: Boolean = false,
//    val data: List<Any>? = null,
//    val status: HttpStatusCode = HttpStatusCode.Processing
//)