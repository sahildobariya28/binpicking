package com.scanner.binpicking.data

import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultErrorModel(
    @SerialName("message")
    val message: String,
    @SerialName("trace")
    val trace: String? = "",
)

@Serializable
data class CommonErrorModel(
    @SerialName("error")
    val error: Boolean,
    @SerialName("message")
    val message: String,
)


suspend fun handleError(response: HttpResponse): DataState {
    return try {
        val loginErrorModel: CommonErrorModel = response.body()

        DataState(
            state = DataConfiguration.OnError,
            errorResponse = loginErrorModel.message,
            responseStatus = response.status
        )
    } catch (e: Exception) {
        val defaultErrorModel: DefaultErrorModel = response.body()
        DataState(
            state = DataConfiguration.OnError,
            errorResponse = defaultErrorModel.message,
            responseStatus = response.status
        )

    }
}

suspend fun showError(response: HttpResponse): DataState {
    return try {
        val defaultErrorModel: DefaultErrorModel = response.body()
        DataState(
            state = DataConfiguration.OnError,
            errorResponse = defaultErrorModel.message,
            responseStatus = response.status
        )
    } catch (e: Exception) {

        val loginErrorModel: CommonErrorModel = response.body()
        DataState(
            state = DataConfiguration.OnError,
            errorResponse = loginErrorModel.message,
            responseStatus = response.status
        )
    }
}