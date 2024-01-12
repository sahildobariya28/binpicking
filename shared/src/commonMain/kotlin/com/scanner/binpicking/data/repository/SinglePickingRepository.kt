package com.scanner.binpicking.data.repository

import com.scanner.binpicking.core.HttpRoutes
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class SinglePickingRepository : SinglePickingService {

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

    @Serializable
    data class CreateOrderModel(
        @SerialName("orderNumber")
        val orderNumber: String,
        @SerialName("Picker")
        val picker: String
    )

    override suspend fun createOrder(
        orderNumber: String,
        picker: String
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        val request = CreateOrderModel(orderNumber, picker)
        val json = Json.encodeToString(request)

        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.CREATE_ORDER)
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            if (response.status.isSuccess()) {
                val orderInfo =
                    parseOrderInfo(response.body<String>().replace("[", "").replace("]", ""))
                Pair(orderInfo, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    @Serializable
    data class ContinuePickingModel(
        @SerialName("id")
        val entityId: String,
    )

    override suspend fun continuePicking(
        entityId: String,
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        val request = ContinuePickingModel(entityId)
        val json = Json.encodeToString(request)

        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.CONTINUE_PICK)
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            if (response.status.isSuccess()) {
                val orderInfo =
                    parseOrderInfo(response.body<String>().replace("[", "").replace("]", ""))
                Pair(orderInfo, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }


    @Serializable
    data class ScanProductModel(
        @SerialName("sku")
        val sku: String,
        @SerialName("id")
        val id: Int,
        @SerialName("requested_qty")
        val requested_qty: Int,
        @SerialName("picker")
        val picker: Int,
    )

    override suspend fun scanProduct(
        sku: String,
        id: Int,
        requested_qty: Int,
        picker: Int
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        val request = ScanProductModel(sku, id, requested_qty, picker)
        val json = Json.encodeToString(request)


        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.SCAN_PRODUCT)
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            if (response.status.isSuccess()) {
                val resp: String = response.body()
                val orderInfo =
                    parseOrderInfo(response.body<String>().replace("[", "").replace("]", ""))
                Pair(orderInfo, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    @Serializable
    data class UpdateStatusModel(

        @SerialName("id")
        val id: List<Int>
    )

    override suspend fun updateStatus(id: List<Int>, entityId: Int, sku: String, unStoredGatherUiQty: Int, picker: Int): Pair<SimpleResponse?, HttpStatusCode?> {
        val request = UpdateStatusModel(id)
        val json = Json.encodeToString(request)


        return try {

            if (entityId != 0 && sku.isNotEmpty() && unStoredGatherUiQty != 0 && picker != 0){
                val scanRequest = ScanProductModel(sku, entityId, unStoredGatherUiQty, picker)
                val scanJson = Json.encodeToString(scanRequest)

                val scanResponse: HttpResponse = client.post {
                    url(HttpRoutes.SCAN_PRODUCT)
                    contentType(ContentType.Application.Json)
                    setBody(scanJson)
                }

                if (scanResponse.status.isSuccess()) {
                    val response: HttpResponse = client.post {
                        url(HttpRoutes.UPDATE_STATUS)
                        contentType(ContentType.Application.Json)
                        setBody(json)
                    }
                    if (response.status.isSuccess()) {
                        val deleteResponse: List<SimpleResponse> = response.body()
                        Pair(deleteResponse[0], response.status)

                    } else {
                        Pair(null, response.status)
                    }
                }else{
                    Pair(null, scanResponse.status)
                }

            }else{
                val response: HttpResponse = client.post {
                    url(HttpRoutes.UPDATE_STATUS)
                    contentType(ContentType.Application.Json)
                    setBody(json)
                }
                if (response.status.isSuccess()) {
                    val deleteResponse: List<SimpleResponse> = response.body()
                    Pair(deleteResponse[0], response.status)

                } else {
                    Pair(null, response.status)
                }
            }



        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    @Serializable
    data class MissingProductModel(
        @SerialName("missing")
        val missingCount: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("parent_id")
        val parentId: Int,
        @SerialName("picker")
        val picker: Int,
    )

    override suspend fun missingProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        unStoredMissingCount: Int,
        storedMissingCount: Int,
        productQty: Int,
        sku: String,
        id: Int,
        productId: Int,
        picker: Int
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        return try {

            if (unStoredGatherUiQty > storedGatherQty) {
                val scanRequest = ScanProductModel(sku, id, unStoredGatherUiQty, picker)
                val scanJson = Json.encodeToString(scanRequest)

                val scanResponse: HttpResponse = client.post {
                    url(HttpRoutes.SCAN_PRODUCT)
                    contentType(ContentType.Application.Json)
                    setBody(scanJson)
                }

                if (scanResponse.status.isSuccess()) {
//                    val resp: SinglePickingModel = parseOrderInfo(
//                        scanResponse.body<String>().replace("[", "").replace("]", "")
//                    )

                    val missingRequest =
                        MissingProductModel(unStoredMissingCount, id, productId, picker)
                    val missingJson = Json.encodeToString(missingRequest)
                    val missingResponse: HttpResponse = client.post {
                        url(HttpRoutes.MISSING_PRODUCT)
                        contentType(ContentType.Application.Json)
                        setBody(missingJson)
                    }
                    if (missingResponse.status.isSuccess()) {
                        val resp: SinglePickingModel = parseOrderInfo(
                            missingResponse.body<String>().replace("[", "").replace("]", "")
                        )
                        Pair(resp, missingResponse.status)
                    } else {
                        Pair(null, missingResponse.status)
                    }
                } else {
                    Pair(null, scanResponse.status)
                }
            } else {
                val missingRequest =
                    MissingProductModel(unStoredMissingCount, id, productId, picker)
                val missingJson = Json.encodeToString(missingRequest)
                val missingResponse: HttpResponse = client.post {
                    url(HttpRoutes.MISSING_PRODUCT)
                    contentType(ContentType.Application.Json)
                    setBody(missingJson)
                }
                if (missingResponse.status.isSuccess()) {
                    val resp: SinglePickingModel = parseOrderInfo(
                        missingResponse.body<String>().replace("[", "").replace("]", "")
                    )
                    Pair(resp, missingResponse.status)
                } else {
                    Pair(null, missingResponse.status)
                }
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    @Serializable
    data class SkipPreviousProductModel(
        @SerialName("id")
        val id: Int,
        @SerialName("parent_id")
        val parentId: Int
    )

    override suspend fun skipProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        sku: String,
        picker: Int,
        id: Int,
        parentId: Int,
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        val request = SkipPreviousProductModel(id, parentId)
        val json = Json.encodeToString(request)


        return try {

            if (unStoredGatherUiQty > storedGatherQty) {
                val scanRequest = ScanProductModel(sku, id, unStoredGatherUiQty, picker)
                val scanJson = Json.encodeToString(scanRequest)

                val scanResponse: HttpResponse = client.post {
                    url(HttpRoutes.SCAN_PRODUCT)
                    contentType(ContentType.Application.Json)
                    setBody(scanJson)
                }

                if (scanResponse.status.isSuccess()) {
                    val response: HttpResponse = client.post {
                        url(HttpRoutes.SKIP_PRODUCT)
                        contentType(ContentType.Application.Json)
                        setBody(json)
                    }
                    if (response.status.isSuccess()) {
                        val resp: SinglePickingModel =
                            parseOrderInfo(
                                response.body<String>().replace("[", "").replace("]", "")
                            )
                        Pair(resp, response.status)
                    } else {
                        Pair(null, response.status)
                    }
                } else {
                    Pair(null, scanResponse.status)
                }
            } else {
                val response: HttpResponse = client.post {
                    url(HttpRoutes.SKIP_PRODUCT)
                    contentType(ContentType.Application.Json)
                    setBody(json)
                }
                if (response.status.isSuccess()) {
                    val resp: SinglePickingModel =
                        parseOrderInfo(response.body<String>().replace("[", "").replace("]", ""))
                    Pair(resp, response.status)
                } else {
                    Pair(null, response.status)
                }
            }


        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun previousProduct(
        id: Int,
        parentId: Int,
    ): Pair<SinglePickingModel?, HttpStatusCode?> {

        val request = SkipPreviousProductModel(id, parentId)
        val json = Json.encodeToString(request)


        return try {
            val response: HttpResponse = client.post {
                url(HttpRoutes.PREVIOUS_PRODUCT)
                contentType(ContentType.Application.Json)
                setBody(json)
            }
            if (response.status.isSuccess()) {
                val resp: SinglePickingModel =
                    parseOrderInfo(response.body<String>().replace("[", "").replace("]", ""))
                Pair(resp, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {
            Pair(null, HttpStatusCode.InternalServerError)
        }
    }


    private fun parseOrderInfo(json: String): SinglePickingModel {
        val jsonObject = Json.parseToJsonElement(json).jsonObject
        if (isSuccessResponse(jsonObject)) {
            var isChangedProduct: Int = if (jsonObject["change_product"] != null) {
                if (jsonObject["change_product"].toString().equals("true")) 1 else 0
            } else {
                -1
            }
            val isFinished = if (jsonObject["proccess_finish"] != null) {
                jsonObject["proccess_finish"].toString().equals("true")
            } else {
                false
            }

            return if (isChangedProduct == 0) {
                val orderGetQty =
                    jsonObject["data"]?.jsonObject?.get("ordergetqty").toString().replace("\"", "")
                        .toInt()
                val orderTotalQty =
                    jsonObject["data"]?.jsonObject?.get("ordertotalqty").toString()
                        .replace("\"", "")
                        .toInt()
                val missing =
                    jsonObject["data"]?.jsonObject?.get("missing").toString().replace("\"", "")
                val credit =
                    jsonObject["data"]?.jsonObject?.get("credit").toString().replace("\"", "")
                val gatherQty =
                    jsonObject["data"]?.jsonObject?.get("gather_qty").toString().replace("\"", "")

                SinglePickingModel(
                    isSuccess = true,
                    isError = false,
                    isChangeProduct = isChangedProduct,
                    proccessFinish = isFinished,
                    ordertotalqty = orderTotalQty,
                    ordergetqty = orderGetQty,
                    missing = missing,
                    credit = credit,
                    gatherQty = gatherQty
                )
            } else if (isFinished) {
                SinglePickingModel(
                    isSuccess = true,
                    isError = false,
                    isChangeProduct = isChangedProduct,
                    proccessFinish = isFinished,
                )
            } else {
                val colorCode =
                    jsonObject["data"]?.jsonObject?.get("color_code").toString().replace("\"", "")
                val createdAt =
                    jsonObject["data"]?.jsonObject?.get("created_at").toString().replace("\"", "")
                val credit =
                    jsonObject["data"]?.jsonObject?.get("credit").toString().replace("\"", "")
                val entityId =
                    jsonObject["data"]?.jsonObject?.get("entity_id").toString().replace("\"", "")
                val gatherQty =
                    jsonObject["data"]?.jsonObject?.get("gather_qty").toString().replace("\"", "")
                val imageUrl =
                    jsonObject["data"]?.jsonObject?.get("image_url").toString().replace("\"", "")
                val inMissing =
                    jsonObject["data"]?.jsonObject?.get("in_missing").toString().replace("\"", "")
                val location =
                    jsonObject["data"]?.jsonObject?.get("location").toString().replace("\"", "")
                val mainRefEntityId =
                    jsonObject["data"]?.jsonObject?.get("main_ref_entity_id").toString()
                        .replace("\"", "")
                val missing =
                    jsonObject["data"]?.jsonObject?.get("missing").toString().replace("\"", "")
                val missingPicker =
                    jsonObject["data"]?.jsonObject?.get("missing_picker").toString()
                        .replace("\"", "")
                val orderNumber =
                    jsonObject["data"]?.jsonObject?.get("order_number").toString().replace("\"", "")
                val orderGetQty =
                    jsonObject["data"]?.jsonObject?.get("ordergetqty").toString().replace("\"", "")
                        .toInt()
                val orderTotalQty =
                    jsonObject["data"]?.jsonObject?.get("ordertotalqty").toString()
                        .replace("\"", "")
                        .toInt()
                val picker =
                    jsonObject["data"]?.jsonObject?.get("picker").toString().replace("\"", "")
                val picking =
                    jsonObject["data"]?.jsonObject?.get("picking").toString().replace("\"", "")
                val pickupStatus =
                    jsonObject["data"]?.jsonObject?.get("pickup_status").toString()
                        .replace("\"", "")
                val productName =
                    jsonObject["data"]?.jsonObject?.get("product_name").toString().replace("\"", "")
                val qty = jsonObject["data"]?.jsonObject?.get("qty").toString().replace("\"", "")
                val reason =
                    jsonObject["data"]?.jsonObject?.get("reason").toString().replace("\"", "")
                val refEntityId =
                    jsonObject["data"]?.jsonObject?.get("ref_entity_id").toString()
                        .replace("\"", "")
                val shipping =
                    jsonObject["data"]?.jsonObject?.get("shipping").toString().replace("\"", "")
                val sku = jsonObject["data"]?.jsonObject?.get("sku").toString().replace("\"", "")
                val totalMissing =
                    jsonObject["data"]?.jsonObject?.get("total_missing").toString()
                        .replace("\"", "")
                val totalQty =
                    jsonObject["data"]?.jsonObject?.get("total_qty").toString().replace("\"", "")
                        .toInt()
                val updatedAt =
                    jsonObject["data"]?.jsonObject?.get("updated_at").toString().replace("\"", "")

                SinglePickingModel(
                    isSuccess = true,
                    isError = false,
                    isChangeProduct = isChangedProduct,
                    proccessFinish = isFinished,
                    colorCode = colorCode,
                    createdAt = createdAt,
                    credit = credit,
                    entityId = entityId,
                    gatherQty = gatherQty,
                    imageUrl = imageUrl,
                    inMissing = inMissing,
                    location = location,
                    mainRefEntityId = mainRefEntityId,
                    missing = missing,
                    missingPicker = missingPicker,
                    orderNumber = orderNumber,
                    ordergetqty = orderGetQty,
                    ordertotalqty = orderTotalQty,
                    picker = picker,
                    picking = picking,
                    pickupStatus = pickupStatus,
                    productName = productName,
                    qty = qty,
                    reason = reason,
                    refEntityId = refEntityId,
                    shipping = shipping,
                    sku = sku,
                    totalMissing = totalMissing,
                    totalQty = totalQty,
                    updatedAt = updatedAt,
                )
            }
        } else {
            val errorMsg = if (jsonObject["success"] != null) {
                if (jsonObject["Message"] != null) {
                    jsonObject["Message"].toString().replace("\"", "")
                } else {
                    jsonObject["error"].toString().replace("\"", "")
                }

            } else if (jsonObject["error"] != null) {
                jsonObject["error"].toString().replace("\"", "")
            } else if (jsonObject["message"] != null) {
                jsonObject["message"].toString().replace("\"", "")
            } else {
                "Something Want Wrong"
            }
            return SinglePickingModel(isSuccess = false, isError = true, errorMsg = errorMsg)
        }
    }

    private fun isSuccessResponse(jsonObject: JsonObject): Boolean {

        val isError = if (jsonObject["success"] != null) {
            jsonObject["success"].toString().equals("true")
        } else if (jsonObject["error"] != null) {
            jsonObject["error"].toString().isEmpty()
        } else if (jsonObject["message"] != null) {
            jsonObject["message"].toString().isEmpty()
        } else {
            true
        }
        return isError
    }


}