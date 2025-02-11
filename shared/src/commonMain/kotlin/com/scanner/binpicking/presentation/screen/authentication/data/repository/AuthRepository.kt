package com.scanner.binpicking.presentation.screen.authentication.data.repository

import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import com.scanner.binpicking.core.Endpoint
import com.scanner.binpicking.presentation.screen.authentication.data.model.AuthReq
import com.scanner.binpicking.presentation.screen.authentication.data.model.UserDetailRes
import com.scanner.binpicking.data.handleError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AuthRepository {

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
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }

    }

    suspend fun getLogin(
        username: String,
        password: String,
    ): DataState {
        val request = AuthReq(username, password)
        val json = Json.encodeToString(request)
        val response: HttpResponse = client.post {
            url(Endpoint.Login.getUrl())
            setBody(json)
        }
        return if (response.status.isSuccess()) {
            try {
                var accessToken: String = response.body()
                accessToken = accessToken.replace("\"", "")
                getPickerId(accessToken)
            } catch (e: Exception) {
                handleError(response)
            }
        } else {
            handleError(response)
        }
    }

    private suspend fun getPickerId(
        accessToken: String,
    ): DataState {
        return if (accessToken.isNotEmpty()) {
            val response: HttpResponse = client.post {
                url(Endpoint.PickerId.getUrl())
                bearerAuth(accessToken)
            }
            return if (response.status.isSuccess()) {
                try {
                    val userDetailRes: UserDetailRes = response.body()
                    userDetailRes.accessToken = accessToken
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = userDetailRes,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            } else {
                handleError(response)
            }
        } else {
            DataState(
                state = DataConfiguration.OnError,
                errorResponse = "The account sign-in was incorrect. Please wait and try again later.",
                responseStatus = HttpStatusCode.NotFound
            )
        }
    }

}