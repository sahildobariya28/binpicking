package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.LoginService
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPickerIdUseCase(private val repository: LoginService) {

    operator fun invoke(accessToken: String): Flow<Resource<String>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getPickerId(accessToken)

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = fetchedBanners.ifEmpty { "" }
                    emit(Resource.Success(data = domainData, status = status))
                }else{
                    emit(Resource.Error(data = "Not Found", status = status))
                }
            }else{
                emit(Resource.Error(data = "Not Found", status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = "Not Found", status = HttpStatusCode.InternalServerError))
        }
    }
}