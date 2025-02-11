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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.presentation.component.PasswordTextField
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.component.SimpleTextField
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import com.scanner.binpicking.topSafeArea
import rememberMessageBarState

@Composable
fun SignUpScreen(viewModel: SignUpScreenViewModel) {

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
            Column(modifier = Modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(Modifier.height(40.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    textAlign = TextAlign.Start,
                    text = "Hello, There",
                    fontSize = 30.sp,
                    color = LightOnPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    text = "Enter your information below to get\nstated your trips",
                    textAlign = TextAlign.Start,
                    color = LightOnPrimary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(50.dp))
                SimpleTextField(
                    hint = "Enter First Name",
                    text = viewModel.firstName,
                    imageVector = Icons.Default.Face
                ) { newText -> viewModel.firstName = newText }

                Spacer(Modifier.height(15.dp))
                SimpleTextField(
                    hint = "Enter Last Name",
                    text = viewModel.lastName,
                    imageVector = Icons.Default.Face
                ) { newText -> viewModel.lastName = newText }

                Spacer(Modifier.height(15.dp))
                SimpleTextField(
                    hint = "Enter Email Id",
                    text = viewModel.email
                ) { newText -> viewModel.email = newText }

                Spacer(Modifier.height(15.dp))
                SimpleTextField(
                    hint = "Enter Username",
                    text = viewModel.username,
                    imageVector = Icons.Default.Person
                ) { newText -> viewModel.username = newText }

                Spacer(Modifier.height(15.dp))
                PasswordTextField(
                    hint = "Enter Password",
                    text = viewModel.password,
                    isPasswordToggle = viewModel.isPasswordToggle,
                    onTextChange = { viewModel.password = it },
                    onPasswordToggleChange = { viewModel.isPasswordToggle = it })

                Spacer(Modifier.height(15.dp))
                PasswordTextField(
                    hint = "Enter Conform Password",
                    text = viewModel.conformPassword,
                    isPasswordToggle = viewModel.isConformPasswordToggle,
                    onTextChange = { viewModel.conformPassword = it },
                    onPasswordToggleChange = { viewModel.isConformPasswordToggle = it })


                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                        .background(LightOnPrimary)
                        .clickable {
                            viewModel.login()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Create Account",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            if (viewModel.isLoading) {
                ShowLoadingDialog()
            }
        }
    }
}