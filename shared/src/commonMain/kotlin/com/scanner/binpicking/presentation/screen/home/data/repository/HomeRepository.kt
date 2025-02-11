package com.scanner.binpicking.presentation.screen.home.data.repository

import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import com.scanner.binpicking.core.Endpoint
import com.scanner.binpicking.presentation.screen.home.data.model.CreateOrderReq
import com.scanner.binpicking.presentation.screen.home.data.model.DeleteRequest
import com.scanner.binpicking.presentation.screen.home.data.model.DeleteResponse
import com.scanner.binpicking.presentation.screen.home.data.model.OrderList
import com.scanner.binpicking.presentation.screen.home.data.model.UpdateStatusModel
import com.scanner.binpicking.data.handleError
import com.scanner.binpicking.presentation.screen.home.data.model.ContinuePickingReq
import com.scanner.binpicking.presentation.screen.home.data.model.UpdateResponse
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.util.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object HomeRepository {

    private val client = HttpClient {

        install(Logging) {
            level = LogLevel.NONE
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "NapierLogSystem   OrderListRepository: ", message = message)
                }
            }
        }

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

        install(ResponseObserver) {
            onResponse { response ->
                println("errtyertyretyet ::: HTTP status: ${response.status.value}>>${response.responseTime.timestamp - response.requestTime.timestamp}ms, url : ${response.call.request.url}")
            }
        }

        install(DefaultRequest) {

        }
    }.also { initLogger() }

    suspend fun getOrder(
        pageSize: Int,
        currentPage: Int,
        filter: String,
    ): DataState {
        val response: HttpResponse = client.get {
            url(Endpoint.GetOrderList.getUrl() + "?page=${currentPage}&pageSize=$pageSize$filter")
            bearerAuth(AppConfig.getToken())
        }


        return when {
            response.status.isSuccess() -> {
                try {
                    val orderList: OrderList = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = orderList,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            }
            else -> {
                handleError(response)
            }
        }
    }

    suspend fun createOrder(
        orderNumber: String,
        picker: String
    ): DataState {

        val request = CreateOrderReq(orderNumber, picker)
        val json = Json.encodeToString(request)

        val response: HttpResponse = client.post {
            url(Endpoint.CreateOrder.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }

        return when {
            response.status.isSuccess() -> {
                try {
                    val orderInfo: PickingModelWrapper = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = orderInfo,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            }
            else -> {
                handleError(response)
            }
        }
    }

    suspend fun deleteOrder(selectedOrderEntityId: List<Int>): DataState {
        val request = DeleteRequest(selectedOrderEntityId)
        val json = Json.encodeToString(request)

        val response: HttpResponse = client.post {
            url(Endpoint.DeleteOrder.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }

        return when {
            response.status.isSuccess() -> {
                try {
                    val deleteResponse: DeleteResponse = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = deleteResponse,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            }
            else -> {
                handleError(response)
            }
        }
    }

    suspend fun updateStatus(
        id: List<Int>
    ): DataState {
        val request = UpdateStatusModel(id)
        val json = Json.encodeToString(request)

        val response: HttpResponse = client.post {
            url(Endpoint.MoveToPending.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }

        return when {
            response.status.isSuccess() -> {
                try {
                    val resp: UpdateResponse = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = resp,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            }
            else -> {
                handleError(response)
            }
        }

    }

    suspend fun continuePicking(
        entityId: String
    ): DataState {
        val request = ContinuePickingReq(entityId)
        val json = Json.encodeToString(request)

        val response: HttpResponse = client.post {
            url(Endpoint.ContinuePick.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }
        return when {
            response.status.isSuccess() -> {
                try {
                    val resp: PickingModelWrapper = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = resp,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            }
            else -> {
                handleError(response)
            }
        }

    }
}

