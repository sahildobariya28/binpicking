package com.scanner.binpicking.presentation.screen.picking.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkipPreviousReq(
    @SerialName("id")
    val id: Int,
    @SerialName("parent_id")
    val parentId: Int
)