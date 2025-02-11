package com.scanner.binpicking.presentation.screen.orderitem.data.repository

import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import com.scanner.binpicking.core.Endpoint
import com.scanner.binpicking.data.handleError
import com.scanner.binpicking.presentation.screen.orderitem.data.model.OrderItemsRes
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
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object OrderItemsRepository {

    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.NONE
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "asdfasdfadf OrderItemListRepository: ", message = message)
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
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }

    }.also { initLogger() }

    suspend fun getOrderItemList(
        entityId: String
    ): DataState {
        val response: HttpResponse = client.get {
            url(Endpoint.GetOrderItemList.getUrl() + "?entityId=$entityId")
            bearerAuth(AppConfig.getToken())
        }
        return when {
            response.status.isSuccess() -> {
                try {
                    val orderItemList: List<OrderItemsRes> = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = orderItemList[0],
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