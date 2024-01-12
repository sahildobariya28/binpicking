package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.OrderItemListService
import com.scanner.binpicking.domain.mode.ItemListModelList
import com.scanner.binpicking.domain.mode.ItemModel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOrderItemListUseCase(private val repository: OrderItemListService) {

    operator fun invoke(entityId: String): Flow<Resource<ItemListModelList?>> = flow {

        try {
            emit(Resource.Loading())
            val(orderData, status) = repository.getOrderItemList(entityId)

            if (status != null) {
                if (status.isSuccess()){
                    emit(Resource.Success(data = orderData, status = status))
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