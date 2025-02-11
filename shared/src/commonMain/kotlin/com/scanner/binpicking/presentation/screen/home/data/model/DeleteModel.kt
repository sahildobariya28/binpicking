package com.scanner.binpicking.presentation.screen.home.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteRequest(
    @SerialName("id")
    val id: List<Int>
)

@Serializable
data class DeleteResponse(
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val isSuccess: Boolean
)