package com.scanner.binpicking.domain.mode.singlepicking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedOrderInfo(
    @SerialName("data")
    val orderData: OrderData,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class OrderData(
    @SerialName("color_code")
    val colorCode: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("credit")
    val credit: String,
    @SerialName("entity_id")
    val entityId: String,
    @SerialName("gather_qty")
    val gatherQty: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("in_missing")
    val inMissing: String,
    @SerialName("location")
    val location: String,
    @SerialName("main_ref_entity_id")
    val mainRefEntityId: String,
    @SerialName("missing")
    val missing: String,
    @SerialName("missing_picker")
    val missingPicker: String,
    @SerialName("order_number")
    val orderNumber: String,
    @SerialName("ordergetqty")
    val ordergetqty: Int,
    @SerialName("ordertotalqty")
    val ordertotalqty: Int,
    @SerialName("picker")
    val picker: String,
    @SerialName("picking")
    val picking: String,
    @SerialName("pickup_status")
    val pickupStatus: String,
    @SerialName("product_name")
    val productName: String,
    @SerialName("qty")
    val qty: String,
    @SerialName("reason")
    val reason: String,
    @SerialName("ref_entity_id")
    val refEntityId: String,
    @SerialName("shipping")
    val shipping: String,
    @SerialName("sku")
    val sku: String,
    @SerialName("total_missing")
    val totalMissing: String,
    @SerialName("total_qty")
    val totalQty: Int,
    @SerialName("updated_at")
    val updatedAt: String
)