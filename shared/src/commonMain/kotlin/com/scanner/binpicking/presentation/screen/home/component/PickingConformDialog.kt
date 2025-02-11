package com.scanner.binpicking.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.isIos
import com.scanner.binpicking.presentation.screen.home.HomeViewModel
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite

@Composable
fun PickingConformDialog(
    homeViewModel: HomeViewModel,
    onDismiss: () -> Unit,
) {

    AppConfig.getPickerId()?.let { pickerId ->

//        var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        Dialog(onDismissRequest = {
            keyboardController?.hide()
            focusManager.clearFocus()
            onDismiss()
        }) {
            Column(Modifier.fillMaxWidth(.9f).background(NeutralWhite).padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Check Order Number", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                Spacer(Modifier.height(20.dp).fillMaxWidth())

                BasicTextField(
                    modifier = Modifier.height(50.dp).focusRequester(focusRequester),
                    value = "#${homeViewModel.orderNoCheck}",
                    onValueChange = {
                        homeViewModel.orderNoCheck = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            homeViewModel.createOrder(homeViewModel.orderNoCheck, pickerId)
                            onDismiss()
                        }),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        color = LightOnPrimary,
                        fontWeight = FontWeight.Bold
                    ),
                    singleLine = true,
                    maxLines = 1,
                )

                Spacer(Modifier.height(20.dp).fillMaxWidth())

                Row(Modifier.height(50.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Box(
                        Modifier.weight(1f).height(50.dp).clip(RoundedCornerShape(10.dp)).background(
                            LightPrimary)
                            .clickable {
                                println("asdfjfffffdsewe 111+ $isIos")
                                focusManager.clearFocus()
                                focusRequester.requestFocus()
                                keyboardController?.show()
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Edit", fontWeight = FontWeight.SemiBold, color = NeutralWhite)
                    }
                    Box(
                        Modifier.weight(1f).height(50.dp).clip(RoundedCornerShape(10.dp)).background(
                            LightPrimary)
                            .clickable {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                homeViewModel.createOrder(homeViewModel.orderNoCheck, pickerId)
                                onDismiss()
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Done", fontWeight = FontWeight.SemiBold, color = NeutralWhite)
                    }
                }
            }

        }
    }
}