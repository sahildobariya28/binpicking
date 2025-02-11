package com.scanner.binpicking.presentation.screen.picking.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateStatusReq(
    @SerialName("id")
    val id: List<Int>
)

@Serializable
data class UpdateStatusRes(
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val isSuccess: Boolean
)