package com.scanner.binpicking.domain.mode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val isSuccess: Boolean
)