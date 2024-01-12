package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.OrderListService
import com.scanner.binpicking.domain.mode.OrderList
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOrderListUseCase(private val repository: OrderListService) {

    operator fun invoke(pageSize: Int, currentPage: Int, token: String, filter: String): Flow<Resource<OrderList?>> = flow {

        try {
            emit(Resource.Loading())
            val(orderData, status) = repository.getOrder(pageSize, currentPage, token, filter)

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