package com.scanner.binpicking.data.services

import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.OrderList
import io.ktor.http.HttpStatusCode

interface OrderListService {

    suspend fun getOrder(pageSize: Int, currentPage: Int, token: String, filter: String): Pair<OrderList?, HttpStatusCode?>

    suspend fun deleteOrder(
        selectedOrderEntityId: List<Int>,
    ): Pair<SimpleResponse?, HttpStatusCode?>

}