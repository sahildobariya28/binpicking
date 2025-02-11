package com.scanner.binpicking.presentation.screen.picking.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LowQtyDataUpdate(
    @SerialName("message")
    val message: String = "",
    @SerialName("success")
    val success: Boolean = false
)