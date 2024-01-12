package com.scanner.binpicking.presentation.screen.signinscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.core.SingleDataState
import com.scanner.binpicking.domain.usecase.GetPickerIdUseCase
import com.scanner.binpicking.domain.usecase.LoginUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInViewModel : ViewModel(), KoinComponent {

    var emailText: String by mutableStateOf("")
    var passwordText: String by mutableStateOf("")

//    var emailText: String by mutableStateOf("AJAY")
//    var passwordText: String by mutableStateOf("0uW1G453ozZjWM6")
//    var emailText: String by mutableStateOf("binpickingapp")
//    var passwordText: String by mutableStateOf("binpickingapp@123")
    var isPasswordToggle: Boolean by mutableStateOf(false)
    var isLoading: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

    private val loginUseCase: LoginUseCase by inject()
    private val getPickerIdUseCase: GetPickerIdUseCase by inject()

    private val _accessToken = MutableStateFlow(SingleDataState())
    private val _pickerId = MutableStateFlow(SingleDataState())

    val accessToken: StateFlow<SingleDataState> = _accessToken
    val pickerId: StateFlow<SingleDataState> = _pickerId


    fun getLogin(username: String, password: String) {

        loginUseCase.invoke(username, password).onEach {
            when (it) {
                is Resource.Loading -> {
                    _accessToken.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _accessToken.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _accessToken.value =
                            SingleDataState(
                                isLoading = false,
                                data = "Not Found",
                                status = it.status
                            )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPickerId(accessToken: String) {

        getPickerIdUseCase.invoke(accessToken).onEach {
            when (it) {
                is Resource.Loading -> {
                    _pickerId.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _pickerId.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _pickerId.value =
                            SingleDataState(
                                isLoading = false,
                                data = "Not Found",
                                status = it.status
                            )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


}