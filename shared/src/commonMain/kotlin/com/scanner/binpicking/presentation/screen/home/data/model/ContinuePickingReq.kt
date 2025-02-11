package com.scanner.binpicking.presentation.screen.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContinuePickingReq(
    @SerialName("id")
    val entityId: String,
)