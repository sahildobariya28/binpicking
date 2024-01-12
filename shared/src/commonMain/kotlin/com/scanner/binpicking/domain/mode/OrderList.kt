package com.scanner.binpicking.domain.mode


import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Immutable
@Serializable
data class OrderList(
    @SerialName("data")
    val data: List<Order> = listOf(),
    @SerialName("success")
    val success: Boolean,
)


@Serializable
data class Order(
//    val id: String = UUID.randomUUID(),
    @SerialName("ending_time")
    val endingTime: String?,
    @SerialName("entity_id")
    val entityId: String,
    @SerialName("interval")
    val interval: String?,
    @SerialName("issingle")
    val isSingle: String,
    @SerialName("no_of_order")
    val noOfOrder: String,
    @SerialName("orders")
    val orders: List<String>,
    @SerialName("picker")
    val picker: String,
    @SerialName("starting_time")
    val startingTime: String?,
    @SerialName("status")
    val status: String,
    @SerialName("action_status")
    val actionStatus: Int,
    var isSelected: Boolean = false
)