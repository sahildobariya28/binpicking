package com.scanner.binpicking.presentation.screen.picking.data.repository

import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.core.Endpoint
import com.scanner.binpicking.presentation.screen.picking.data.model.LogErrorReq
import com.scanner.binpicking.util.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LogErrorRepository {

    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "jdslkfjlksdf", message = message)
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
                Napier.v(
                    tag = "jdslkfjlksdf",
                    message = "HTTP status: ${response.status.value}>>${response.responseTime.timestamp - response.requestTime.timestamp}ms"
                )
            }
        }

        install(DefaultRequest) {

        }
    }.also { initLogger() }


    suspend fun logError(logErrorModel: String){
        val request = LogErrorReq(message = logErrorModel)
        val json = Json.encodeToString(request)
        client.post {
            url(Endpoint.LogError.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }

    }
}