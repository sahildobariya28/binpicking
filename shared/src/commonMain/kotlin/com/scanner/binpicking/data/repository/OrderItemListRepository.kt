package com.scanner.binpicking.data.repository

import com.scanner.binpicking.core.HttpRoutes
import com.scanner.binpicking.data.services.OrderItemListService
import com.scanner.binpicking.domain.mode.ItemListModelList
import com.scanner.binpicking.domain.mode.ItemModel
import com.scanner.binpicking.domain.mode.OrderList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class OrderItemListRepository: OrderItemListService {

    private val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {

        }

    }

    override suspend fun getOrderItemList(entityId: String): Pair<ItemListModelList?, HttpStatusCode?> {
        return try {
            val response: HttpResponse = client.get {
                url(HttpRoutes.GET_ORDER_ITEM_LIST + "?entityId=$entityId")
            }
            if (response.status.isSuccess()) {
                val orderList: List<ItemListModelList> = response.body()
                Pair(orderList[0], response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }
}