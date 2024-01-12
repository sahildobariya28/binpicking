package com.scanner.binpicking.data.repository

import com.scanner.binpicking.core.HttpRoutes
import com.scanner.binpicking.data.services.LoginService
import com.scanner.binpicking.domain.mode.LoginModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginRepository : LoginService {

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

    override suspend fun getLogin(
        username: String,
        password: String
    ): Pair<String, HttpStatusCode?> {

        val request = LoginModel(username, password)
        val json = Json.encodeToString(request)
        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.LOGIN)
                setBody(json)
            }
            if (response.status.isSuccess()) {
                var accessToken: String = response.body()
                accessToken = accessToken.replace("\"", "")
                Pair(accessToken, response.status)
            } else {
                Pair("", response.status)
            }

        } catch (e: Exception) {
            Pair("", HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getPickerId(accessToken: String): Pair<String, HttpStatusCode?> {

        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.PICKER_ID)
                bearerAuth(accessToken)
            }

            if (response.status.isSuccess()) {
                var pickerId: String = response.body()
                pickerId = pickerId.replace("\"", "")
                Pair(pickerId, response.status)
            } else {
                Pair("", response.status)
            }

        } catch (e: Exception) {
            Pair("", HttpStatusCode.InternalServerError)
        }
    }

}