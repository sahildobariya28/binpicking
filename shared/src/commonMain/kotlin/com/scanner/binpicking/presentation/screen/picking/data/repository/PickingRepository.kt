package com.scanner.binpicking.presentation.screen.picking.data.repository

import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import com.scanner.binpicking.core.Endpoint
import com.scanner.binpicking.presentation.screen.picking.data.model.UpdateStatusRes
import com.scanner.binpicking.data.handleError
import com.scanner.binpicking.presentation.screen.picking.data.model.LowQtyDataUpdate
import com.scanner.binpicking.presentation.screen.picking.data.model.MissingProductReq
import com.scanner.binpicking.presentation.screen.picking.data.model.ScanProductReq
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.presentation.screen.picking.data.model.SkipPreviousReq
import com.scanner.binpicking.presentation.screen.picking.data.model.UpdateStatusReq
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

object PickingRepository {

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


    suspend fun scanProduct(
        sku: String,
        id: Int,
        requestedQty: Int,
        picker: Int
    ): DataState {
//        val json = """
//{
//    "success": true,
//    "change_product": true,
//    "proccess_finish": false,
//    "data": {
//        "credit": 0.0,
//        "entity_id": "5076558",
//        "gather_qty": 0,
//        "image_url": "https://www.phonelcdparts.com/media/catalog/product/cache/b8115914335f67996909e75a85001ef4/n/1/n10pr-wfblk_1.png",
//        "location": "2-E7-6E",
//        "main_ref_entity_id": "394150",
//        "missing": 0,
//        "order_number": "513209",
//        "ordergetqty": 0,
//        "ordertotalqty": 9,
//        "qty": "1",
//        "ref_entity_id": "394598",
//        "shipping": "FedEx Ground",
//        "sku": "N10PR-WFBLK",
//        "total_qty": 125
//    }
//}
//""".trimIndent()
//        val resp: PickingModelWrapper = Json.decodeFromString(PickingModelWrapper.serializer(), json)
//        return DataState(
//            state = DataConfiguration.OnSuccess,
//            successResponse = resp,
//            responseStatus = HttpStatusCode.OK
//        )

        val request = ScanProductReq(sku, id, requestedQty, picker)
        val json = Json.encodeToString(request)
        val response: HttpResponse = client.post {
            url(Endpoint.ScanProduct.getUrl())
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

    suspend fun missingProduct(
        uiGatherQty: Int,
        dataGatherQty: Int,
        uiMissingQty: Int,
        dataMissingQty: Int,
        totalQty: Int,
        sku: String,
        id: Int,
        productId: Int,
        picker: Int
    ): DataState {

        return if (uiGatherQty > 0 && uiGatherQty <= (totalQty - dataGatherQty)) {
            val scanRequest =
                ScanProductReq(sku, id, (dataGatherQty + uiGatherQty), picker)
            val scanJson = Json.encodeToString(scanRequest)
            val scanResponse: HttpResponse = client.post {
                url(Endpoint.ScanProduct.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(scanJson)
            }

            return if (scanResponse.status.isSuccess()) {

                val missingRequest =
                    MissingProductReq((dataMissingQty + uiMissingQty), id, productId, picker)
                val missingJson = Json.encodeToString(missingRequest)

                val missingResponse: HttpResponse = client.post {
                    url(Endpoint.MissingProduct.getUrl())
                    bearerAuth(AppConfig.getToken())
                    contentType(ContentType.Application.Json)
                    setBody(missingJson)
                }

                if (missingResponse.status.isSuccess()) {
                    try {
                        val resp: PickingModelWrapper = missingResponse.body()
                        DataState(
                            state = DataConfiguration.OnSuccess,
                            successResponse = resp,
                            responseStatus = missingResponse.status
                        )
                    } catch (e: Exception) {
                        handleError(missingResponse)
                    }
                } else {
                    handleError(missingResponse)
                }

            } else {
                handleError(scanResponse)
            }
        } else {

            val missingRequest =
                MissingProductReq((dataMissingQty + uiMissingQty), id, productId, picker)
            val missingJson = Json.encodeToString(missingRequest)
            val missingResponse: HttpResponse = client.post {
                url(Endpoint.MissingProduct.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(missingJson)
            }

            if (missingResponse.status.isSuccess()) {
                try {
                    val resp: PickingModelWrapper = missingResponse.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = resp,
                        responseStatus = missingResponse.status
                    )
                } catch (e: Exception) {
                    handleError(missingResponse)
                }
            } else {
                handleError(missingResponse)
            }
        }


    }

    suspend fun skipProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        sku: String,
        picker: Int,
        id: Int,
        parentId: Int
    ): DataState {

        val request = SkipPreviousReq(id, parentId)
        val json = Json.encodeToString(request)

        return if (unStoredGatherUiQty > storedGatherQty) {
            val scanRequest = ScanProductReq(sku, id, unStoredGatherUiQty, picker)
            val scanJson = Json.encodeToString(scanRequest)

            val scanResponse: HttpResponse = client.post {
                url(Endpoint.ScanProduct.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(scanJson)
            }

            if (scanResponse.status.isSuccess()) {
                val response: HttpResponse = client.post {
                    url(Endpoint.SkipProduct.getUrl())
                    bearerAuth(AppConfig.getToken())
                    contentType(ContentType.Application.Json)
                    setBody(json)
                }
                if (response.status.isSuccess()) {
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
                } else {
                    handleError(response)
                }
            } else {
                handleError(scanResponse)
            }
        } else {
            val response: HttpResponse = client.post {
                url(Endpoint.SkipProduct.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            if (response.status.isSuccess()) {
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
            } else {
                handleError(response)
            }
        }
    }

    suspend fun previousProduct(
        id: Int,
        parentId: Int
    ): DataState {

        val request = SkipPreviousReq(id, parentId)
        val json = Json.encodeToString(request)

        val response: HttpResponse = client.post {
            url(Endpoint.PreviousProduct.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody(json)
        }

        return if (response.status.isSuccess()) {
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
        } else {
            handleError(response)
        }
    }


    suspend fun updateStatus(
        id: List<Int>,
        entityId: Int = 0,
        sku: String = "",
        unStoredGatherUiQty: Int = 0,
        picker: Int = 0
    ): DataState {
        val request = UpdateStatusReq(id)
        val json = Json.encodeToString(request)

        if (entityId != 0 && sku.isNotEmpty() && unStoredGatherUiQty != 0 && picker != 0) {
            val scanRequest = ScanProductReq(sku, entityId, unStoredGatherUiQty, picker)
            val scanJson = Json.encodeToString(scanRequest)

            val scanResponse: HttpResponse = client.post {
                url(Endpoint.SkipProduct.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(scanJson)
            }

            return if (scanResponse.status.isSuccess()) {
                val response: HttpResponse = client.post {
                    url(Endpoint.MoveToPending.getUrl())
                    bearerAuth(AppConfig.getToken())
                    contentType(ContentType.Application.Json)
                    setBody(json)
                }
                return if (response.status.isSuccess()) {
                    try {
                        val resp: UpdateStatusRes = response.body()
                        DataState(
                            state = DataConfiguration.OnSuccess,
                            successResponse = resp,
                            responseStatus = response.status
                        )
                    } catch (e: Exception) {
                        handleError(response)
                    }
                } else {
                    handleError(response)
                }
            } else {
                handleError(scanResponse)
            }

        } else {
            val response: HttpResponse = client.post {
                url(Endpoint.MoveToPending.getUrl())
                bearerAuth(AppConfig.getToken())
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            return if (response.status.isSuccess()) {
                try {
                    val deleteResponse: UpdateStatusRes = response.body()
                    DataState(
                        state = DataConfiguration.OnSuccess,
                        successResponse = deleteResponse,
                        responseStatus = response.status
                    )
                } catch (e: Exception) {
                    handleError(response)
                }
            } else {
                handleError(response)
            }
        }
    }

    suspend fun lowQtyDataUpdate(
        sku: String
    ): DataState {

        val response: HttpResponse = client.post {
            url(Endpoint.LowQtyDataUpdate.getUrl())
            bearerAuth(AppConfig.getToken())
            contentType(ContentType.Application.Json)
            setBody("""{"sku": "$sku"}""")
        }

        return if (response.status.isSuccess()) {
            try {
                val resp: LowQtyDataUpdate = response.body()
                DataState(
                    state = DataConfiguration.OnSuccess,
                    successResponse = resp,
                    responseStatus = response.status
                )
            } catch (e: Exception) {
                handleError(response)
            }
        } else {
            handleError(response)
        }
    }

}