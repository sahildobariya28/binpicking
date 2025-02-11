package com.scanner.binpicking.presentation.screen.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderReq(
    @SerialName("orderNumber")
    val orderNumber: String,
    @SerialName("Picker")
    val picker: String
)
