package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateStatusUseCase(private val repository: SinglePickingService) {
    operator fun invoke(
        id: List<Int>,
        entityId: Int = 0,
        sku: String = "",
        unStoredGatherUiQty: Int = 0,
        picker: Int = 0
    ): Flow<Resource<SimpleResponse?>> = flow {

        try {
            emit(Resource.Loading())
            val (fetchedBanners, status) = repository.updateStatus(
                id,
                entityId,
                sku,
                unStoredGatherUiQty,
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