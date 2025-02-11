package com.scanner.binpicking.presentation.screen.authentication

import ContentWithMessageBar
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.crashkios.crashlytics.CrashlyticsKotlin
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.screen.authentication.component.SignInPasswordTextField
import com.scanner.binpicking.presentation.screen.authentication.component.SignInTextField
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import com.scanner.binpicking.topSafeArea
import com.scanner.binpicking.util.beepSound
import rememberMessageBarState

@Composable
fun SignInScreen(viewModel: SignInScreenViewModel) {

    viewModel.messageBarState = rememberMessageBarState()

    ContentWithMessageBar(
        messageBarState = viewModel.messageBarState,
        position = MessageBarPosition.BOTTOM,
        successContainerColor = Colors.LightSuccessPrimary,
        successContentColor = NeutralWhite,
        errorContainerColor = Red,
        errorContentColor = NeutralWhite
    ) {
        Box(modifier = Modifier.fillMaxSize().background(NeutralWhite).padding(top = topSafeArea.dp)) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(Modifier.height(40.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    textAlign = TextAlign.Start,
                    text = "Hey!\nWelcome back",
                    fontSize = 30.sp,
                    color = LightOnPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    text = "Sign In to your account",
                    textAlign = TextAlign.Start,
                    color = LightOnPrimary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(50.dp))
                SignInTextField(
                    "Enter Email / Username",
                    viewModel.username
                ) { newText -> viewModel.username = newText }

                Spacer(Modifier.height(15.dp))
                SignInPasswordTextField(
                    hint = "Enter Password",
                    text = viewModel.password,
                    isPasswordToggle = viewModel.isPasswordToggle,
                    onTextChange = { viewModel.password = it },
                    onPasswordToggleChange = { viewModel.isPasswordToggle = it })


                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier.height(65.dp).fillMaxWidth().padding(horizontal = 30.dp)
                        .clip(RoundedCornerShape(50.dp)).background(
                            LightOnPrimary
                        ).clickable {
                            beepSound()
                            viewModel.login()
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

                Column(modifier = Modifier.padding(16.dp)) {
                    val agreementText = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Don’t have an Account? ")
                        }

                        val signUpTextStart = length
                        append("Register now")
                        val signUpTextEnd = length

                        addStringAnnotation("URL", "signUp", signUpTextStart, signUpTextEnd)
                    }

                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = agreementText,
                        style = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp),
                        onClick = { offset ->


                            agreementText.getStringAnnotations("URL", offset, offset)
                                .firstOrNull()
                                ?.let { annotation ->
                                    if (annotation.item == "signUp") {
                                        viewModel.onNavigateToSignUpScreen()
                                    }
                                }
                        }
                    )
                }
            }

            if (viewModel.isLoading) {
                ShowLoadingDialog()
            }
        }
    }
}









