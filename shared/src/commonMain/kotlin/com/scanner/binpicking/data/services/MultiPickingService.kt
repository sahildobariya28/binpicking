package com.scanner.binpicking.data.services

import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import io.ktor.http.HttpStatusCode

interface MultiPickingService {

    suspend fun createOrder(
        orderNumber: String,
        picker: String
    ): Pair<SinglePickingModel?, HttpStatusCode?>
}