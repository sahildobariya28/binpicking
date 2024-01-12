package com.scanner.binpicking.domain.mode.singlepicking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderCompletedItem(
    @SerialName("proccess_finish")
    val proccessFinish: Boolean,
    @SerialName("success")
    val success: Boolean
)