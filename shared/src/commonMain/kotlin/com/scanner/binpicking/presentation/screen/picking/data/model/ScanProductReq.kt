package com.scanner.binpicking.presentation.screen.picking.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScanProductReq(
    @SerialName("sku")
    val sku: String,
    @SerialName("id")
    val id: Int,
    @SerialName("requested_qty")
    val requestedQty: Int,
    @SerialName("picker")
    val picker: Int,
)