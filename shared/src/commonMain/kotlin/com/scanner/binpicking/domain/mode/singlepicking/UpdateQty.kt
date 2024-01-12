package com.scanner.binpicking.domain.mode.singlepicking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateQtyItem(
    @SerialName("change_product")
    val changeProduct: Boolean,
    @SerialName("data")
    val data: Data,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class Data(
    @SerialName("credit")
    val credit: String,
    @SerialName("gather_qty")
    val gatherQty: String,
    @SerialName("missing")
    val missing: Int,
    @SerialName("order_pick_qty")
    val orderPickQty: Int,
    @SerialName("ordergetqty")
    val ordergetqty: Int,
    @SerialName("ordertotalqty")
    val ordertotalqty: Int
)