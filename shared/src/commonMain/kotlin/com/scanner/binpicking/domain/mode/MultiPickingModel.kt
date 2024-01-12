package com.scanner.binpicking.domain.mode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultiPickingModel(
    @SerialName("data")
    val data: List<MultiPickingData>,
    @SerialName("number_of_order")
    val numberOfOrder: Int,
    @SerialName("picker")
    val picker: Int
)

@Serializable
data class MultiPickingData(
    @SerialName("bin_number")
    val binNumber: String = "",
    @SerialName("color_code")
    val colorCode: String = "",
    @SerialName("ord_number")
    val ordNumber: Int = 0
)