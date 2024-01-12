package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScanProductUseCase(private val repository: SinglePickingService) {

    operator fun invoke(sku: String, id: Int, requested_qty : Int, picker : Int): Flow<Resource<SinglePickingModel?>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.scanProduct(sku, id, requested_qty, picker)

            if (status != null) {
                if (status.isSuccess()){
                    emit(Resource.Success(data = fetchedBanners, status = status))
                }else{
                    emit(Resource.Error(data = null, status = status))
                }
            }else{
                emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
        }
    }
}