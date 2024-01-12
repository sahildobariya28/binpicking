package com.scanner.binpicking.data.services

import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.domain.mode.singlepicking.UpdateQtyItem
import io.ktor.http.HttpStatusCode

interface SinglePickingService {
    suspend fun createOrder(
        orderNumber: String,
        picker: String
    ): Pair<SinglePickingModel?, HttpStatusCode?>

    suspend fun continuePicking(
        entityId: String,
    ): Pair<SinglePickingModel?, HttpStatusCode?>

    suspend fun scanProduct(
        sku: String,
        id: Int,
        requested_qty: Int,
        picker: Int
    ): Pair<SinglePickingModel?, HttpStatusCode?>

    suspend fun updateStatus(
        id: List<Int>, entityId: Int, sku: String, unStoredGatherUiQty: Int, picker: Int
    ): Pair<SimpleResponse?, HttpStatusCode?>

    suspend fun missingProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        unStoredMissingCount: Int,
        storedMissingCount: Int,
        productQty: Int,
        sku: String,
        id: Int,
        productId: Int,
        picker: Int
    ): Pair<SinglePickingModel?, HttpStatusCode?>

    suspend fun skipProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        sku: String,
        picker: Int, id: Int,
        parentId: Int
    ): Pair<SinglePickingModel?, HttpStatusCode?>

    suspend fun previousProduct(id: Int, parentId: Int): Pair<SinglePickingModel?, HttpStatusCode?>
}