package com.scanner.binpicking.presentation.screen.home.data.model


import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Immutable
@Serializable
data class OrderList(
    @SerialName("success")
    var success: Boolean,
    @SerialName("data")
    var data: List<Order> = listOf(),
)

@Serializable
data class Order(
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
    val orders: ListOfOrder,
    @SerialName("picker")
    val picker: String?,
    @SerialName("starting_time")
    val startingTime: String?,
    @SerialName("status")
    val status: String,
    @SerialName("action_status")
    val actionStatus: Int,
    var isSelected: Boolean = false
)

@Serializable
data class ListOfOrder(
    @SerialName("customer_name")
    val customerName: String,
    @SerialName("order_number")
    val orderNumber: String
)