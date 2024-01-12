package com.scanner.binpicking.presentation.screen.signinscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.presentation.component.PasswordTextField
import com.scanner.binpicking.presentation.component.ShowErrorDialog
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.component.SimpleTextField
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.topSafeArea
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
inline fun SignInScreen(crossinline onSuccessLogin: () -> Unit) {

    val viewModel = getViewModel(
        key = "signin-view-model",
        factory = viewModelFactory {
            SignInViewModel()
        }
    )

    Box(modifier = Modifier.fillMaxSize().padding(top = topSafeArea.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(Modifier.height(40.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                textAlign = TextAlign.Start,
                text = "Hey!\nWelcome back",
                fontSize = 30.sp,
                color = Colors.theme,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                text = "Sign In to your account",
                textAlign = TextAlign.Start,
                color = Colors.theme,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(50.dp))
            SimpleTextField(
                "Enter Email",
                viewModel.emailText
            ) { newText -> viewModel.emailText = newText }

            Spacer(Modifier.height(15.dp))
            PasswordTextField(
                hint = "Enter Password",
                text = viewModel.passwordText,
                isPasswordToggle = viewModel.isPasswordToggle,
                onTextChange = { viewModel.passwordText = it },
                onPasswordToggleChange = { viewModel.isPasswordToggle = it })


            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(
                        RoundedCornerShape(50.dp)
                    )
                    .background(Colors.text_theme)
                    .clickable {

                        viewModel.isLoading = true

                        CoroutineScope(Dispatchers.IO).launch {
                            async {
                                viewModel.getLogin(
                                    viewModel.emailText,
                                    viewModel.passwordText
                                )
                            }
                        }

                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

            }
        }

        if (viewModel.isError) {
            ShowErrorDialog("The account sign-in was incorrect. Please wait and try again later.") {
                viewModel.isError = false
            }
        }
        if (viewModel.isLoading) {
            ShowLoadingDialog()
        }


        val loginState by viewModel.accessToken.collectAsState()
        val pickerState by viewModel.pickerId.collectAsState()


        LaunchedEffect(loginState) {
            if (loginState.isLoading) {
                viewModel.isLoading = true
            } else if (loginState.status.isSuccess()) {
                loginState.data?.let { accessToken ->
                    applicationContext?.put(
                        "login_token",
                        accessToken as String
                    )
                    viewModel.getPickerId((accessToken as String))
                }
            } else if (!loginState.status.isSuccess() && loginState.status != HttpStatusCode.Processing) {
                viewModel.isLoading = false
                viewModel.isError = true
            }
        }


        LaunchedEffect(pickerState) {
            if (pickerState.isLoading) {
                viewModel.isLoading = true
            } else if (pickerState.status.isSuccess()) {
                pickerState.data?.let { accessToken ->
                    applicationContext?.put(
                        "picker_id",
                        accessToken as String
                    )
                    viewModel.isLoading = false
                    onSuccessLogin()
//                            router.bringToFront(MainScreenState.HomeScreen)
                }
            } else if (!pickerState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }
    }
}




