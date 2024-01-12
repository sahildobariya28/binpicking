package com.scanner.binpicking.domain.mode

import androidx.compose.runtime.Immutable
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Immutable
@Serializable
data class ItemListModelList(
    @SerialName("data")
    val data: List<ItemModel> = listOf(),
    @SerialName("success")
    val success: Boolean,
)
@Serializable
data class ItemModel(
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
    @SerialName("scan_type")
    val scanType: String,
    @SerialName("sku")
    val sku: String,
    @SerialName("image")
    val image: String,
    @SerialName("total_missing")
    val totalMissing: String,
    @SerialName("updated_at")
    val updatedAt: String
)
