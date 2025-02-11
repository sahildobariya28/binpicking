package com.scanner.binpicking.presentation.screen.picking.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogErrorModel(
    @SerialName("data")
    val data: String,
    @SerialName("error")
    val error: String?
)

@Serializable
data class LogErrorReq(
    @SerialName("message")
    val message: String?
)
