package com.scanner.binpicking.domain.usecase

import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.data.services.LoginService
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val repository: LoginService) {

    operator fun invoke(username: String, password: String): Flow<Resource<String>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getLogin(username, password)

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