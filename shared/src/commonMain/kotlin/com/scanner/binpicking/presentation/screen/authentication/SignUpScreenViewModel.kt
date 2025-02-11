package com.scanner.binpicking.presentation.screen.authentication

import MessageBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SignUpScreenViewModel(
    componentContext: ComponentContext,
    val onNavigateToEmailConformationScreen: () -> Unit
) : ViewModel() {

    lateinit var messageBarState: MessageBarState

    var isLoading by mutableStateOf(false)

    var firstName: String by mutableStateOf("")
    var lastName: String by mutableStateOf("")
    var username: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var conformPassword: String by mutableStateOf("")

    var isPasswordToggle: Boolean by mutableStateOf(false)
    var isConformPasswordToggle: Boolean by mutableStateOf(false)


    fun login() {
        if (firstName.isNotEmpty() && firstName.length >= 3){

            if (lastName.isNotEmpty() && lastName.length >= 3){
                val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
                if (email.isNotEmpty() && emailRegex.matches(email)){

                    if (username.isNotEmpty() && username.length >= 5){
                        val passwordRegex = "^(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>])(?=.*[0-9])(?=.*[a-zA-Z]).{8,}\$".toRegex()
                        if (passwordRegex.matches(password)){
                            if (passwordRegex.matches(conformPassword)){
                                if (password == conformPassword){
                                    onNavigateToEmailConformationScreen()
                                }else{
                                    messageBarState.addError(exception = Exception("password and Conform password is not Match"))
                                }
                            }else{
                                messageBarState.addError(exception = Exception("Please enter valid conform password."))
                            }
                        }else{
                            messageBarState.addError(exception = Exception("Please enter valid password."))
                        }

                    }else{
                        messageBarState.addError(exception = Exception("Please enter valid username."))
                    }
                }else{
                    messageBarState.addError(exception = Exception("Please enter valid Email."))
                }

            }else{
                messageBarState.addError(exception = Exception("Please enter valid last name."))
            }
        }else{
            messageBarState.addError(exception = Exception("Please enter valid first name."))
        }

        if (username.isNotEmpty()){
            if (password.isNotEmpty()){

            }else{
                messageBarState.addError(exception = Exception("Please enter correct password. "))

            }
        }else{
            messageBarState.addError(exception = Exception("Please enter correct username."))

        }
    }
}