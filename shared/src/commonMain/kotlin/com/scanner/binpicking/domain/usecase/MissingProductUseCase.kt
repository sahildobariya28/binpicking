package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.domain.mode.singlepicking.UpdateQtyItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MissingProductUseCase(private val repository: SinglePickingService) {

    operator fun invoke(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        unStoredMissingCount: Int,
        storedMissingCount: Int,
        productQty: Int,
        sku: String,
        id: Int,
        productId: Int,
        picker: Int
    ): Flow<Resource<SinglePickingModel?>> = flow {

        try {
            emit(Resource.Loading())
            val (fetchedBanners, status) = repository.missingProduct(
                unStoredGatherUiQty,
                storedGatherQty,
                unStoredMissingCount,
                storedMissingCount,
                productQty,
                sku,
                id,
                productId,
                picker
            )

            if (status != null) {
                if (status.isSuccess()) {
                    emit(Resource.Success(data = fetchedBanners, status = status))
                } else {
                    emit(Resource.Error(data = null, status = status))
                }
            } else {
                emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
        }
    }
}