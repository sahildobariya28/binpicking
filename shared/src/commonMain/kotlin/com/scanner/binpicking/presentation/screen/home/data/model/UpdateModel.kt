package com.scanner.binpicking.presentation.screen.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateStatusModel(
    @SerialName("id")
    val id: List<Int>
)

@Serializable
data class UpdateResponse(
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val isSuccess: Boolean
)