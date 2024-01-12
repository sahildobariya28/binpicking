package com.scanner.binpicking.data.repository

import com.scanner.binpicking.core.HttpRoutes
import com.scanner.binpicking.data.services.OrderListService
import com.scanner.binpicking.domain.mode.DeleteRequest
import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.OrderList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OrderListRepository : OrderListService {

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

    override suspend fun getOrder(pageSize: Int, currentPage: Int, token: String, filter: String): Pair<OrderList?, HttpStatusCode?> {

        return try {
            val response: HttpResponse = client.get {
                url(HttpRoutes.GET_ORDER_LIST + "?page=$currentPage&pageSize=$pageSize$filter")
            }
            if (response.status.isSuccess()) {
                val orderList: List<OrderList> = response.body()
                Pair(orderList[0], response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun deleteOrder(selectedOrderEntityId: List<Int>): Pair<SimpleResponse?, HttpStatusCode?> {
        return try {
            val request = DeleteRequest(selectedOrderEntityId)
            val json = Json.encodeToString(request)

            val response: HttpResponse = client.post {
                url(HttpRoutes.DELETE_ORDER)
                contentType(ContentType.Application.Json)
                body = json
            }
            if (response.status.isSuccess()) {
                val deleteResponse: List<SimpleResponse> = response.body()
                Pair(deleteResponse[0], response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

//    private fun parseOrderInfo(json: String): List<OrderItem> {
//
//        val listOrderItem = mutableListOf<OrderItem>()
//        val jsonObject = Json.parseToJsonElement(json).jsonArray[0].jsonObject
//        jsonObject["data"]?.jsonArray?.forEach {
//
//            val entityId = jsonObject["entity_id"]?.jsonPrimitive?.contentOrNull?.toIntOrNull() ?: 0
//            val noOfOrder = jsonObject["no_of_order"]?.jsonPrimitive?.contentOrNull?.toIntOrNull() ?: 0
//            val picker = jsonObject["picker"]?.jsonPrimitive?.contentOrNull.orEmpty()
//            val status = jsonObject["status"]?.jsonPrimitive?.contentOrNull?.toIntOrNull() ?: 0
//            val startingTime = jsonObject["starting_time"]?.jsonPrimitive?.contentOrNull.orEmpty()
//            val endingTime = jsonObject["ending_time"]?.jsonPrimitive?.contentOrNull.orEmpty()
//            val interval = jsonObject["interval"]?.jsonPrimitive?.contentOrNull?.toIntOrNull() ?: 0
//            val isSingle = jsonObject["issingle"]?.jsonPrimitive?.contentOrNull == "1"
//            val orders = parseOrder(jsonObject["orders"]?.jsonArray)
//            listOrderItem.add(OrderItem(entityId = entityId,noOfOrder = noOfOrder, picker = picker, status = status, startingTime = startingTime, endingTime = endingTime, interval = interval, isSingle = isSingle, orders = orders))
//        }
//
//        return listOrderItem
//    }
//
//    private fun parseOrder(jsonArray: JsonArray?): List<String> {
//        return jsonArray?.map { it.toString().replace("\"", "") } ?: emptyList()
//    }
}