package com.scanner.binpicking.presentation.screen.authentication

import MessageBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.data.CommonErrorModel
import com.scanner.binpicking.data.DefaultErrorModel
import com.scanner.binpicking.presentation.screen.authentication.data.model.UserDetailRes
import com.scanner.binpicking.presentation.screen.authentication.data.repository.AuthRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInScreenViewModel(
    componentContext: ComponentContext,
    val onNavigateToHomeScreen: () -> Unit,
    val onNavigateToSignUpScreen: () -> Unit
) : ViewModel() {

    lateinit var messageBarState: MessageBarState

    var isLoading by mutableStateOf(false)

    var username: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    var isPasswordToggle: Boolean by mutableStateOf(false)


    fun login() {
        if (username.isNotEmpty()){
            if (password.isNotEmpty()){
                CoroutineScope(Dispatchers.Main).launch {
                    kotlin.runCatching {
                        isLoading = true
                        AuthRepository.getLogin(username, password)
                    }.onSuccess {
                        when (it.state) {
                            DataConfiguration.OnIdle -> TODO()
                            DataConfiguration.OnLoading -> {
                                isLoading = true
                            }
                            DataConfiguration.OnSuccess -> {
                                it.successResponse?.let { data ->
                                    isLoading = false
                                    try {
                                        applicationContext?.put(
                                            "login_token",
                                            (data as UserDetailRes).accessToken
                                        )
                                        applicationContext?.put(
                                            "picker_id",
                                            (data as UserDetailRes).userId
                                        )
                                        applicationContext?.put(
                                            "picker_name",
                                            (data as UserDetailRes).name
                                        )
                                        applicationContext?.put(
                                            "picker_role",
                                            (data as UserDetailRes).role
                                        )
                                        onNavigateToHomeScreen()

                                    }catch (e: Exception){
                                        when (data) {
                                            is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                            is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                            else -> { messageBarState.addError(exception = Exception(e.message)) }
                                        }
                                    }
                                }
                            }
                            DataConfiguration.OnError -> {
                                isLoading = false
                                messageBarState.addError(exception = Exception("${it.errorResponse}"))
                            }
                        }


                    }.onFailure {
                        messageBarState.addError(exception = Exception("${it.message}"))
                        isLoading = false
                    }
                }
            }else{
                messageBarState.addError(exception = Exception("Please enter correct password."))
            }
        }else{
            messageBarState.addError(exception = Exception("Please enter correct username."))
        }
    }
}