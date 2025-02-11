package com.scanner.binpicking.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.isIos
import com.scanner.binpicking.presentation.screen.home.HomeViewModel
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightOnSecondary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import kotlinx.coroutines.DelicateCoroutinesApi

@Composable
fun PickingDialog(
    homeViewModel: HomeViewModel,
    onDismiss: () -> Unit,
) {


    AppConfig.getPickerId()?.let { pickerId ->

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        Dialog(onDismissRequest = {
            keyboardController?.hide()
            focusManager.clearFocus()
            homeViewModel.orderNoCheck
            onDismiss()
        }) {
            LaunchedEffect(Unit) {
                println("asdfjfffffdsewe + $isIos")
//                focusManager.clearFocus()
                focusRequester.requestFocus()
                if (isIos) {
                    keyboardController?.show()
                } else {
                    keyboardController?.hide()
                }
            }
            Column(Modifier.fillMaxSize(.7f)
                .clickable {
                    keyboardController?.show()
                    focusRequester.requestFocus()
                }) {
                Box(
                    Modifier.fillMaxWidth().fillMaxHeight(.3f).clip(
                        RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 15.dp
                        )
                    ).background(Color(0xffea2a25)).padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ORDER NUMBER SCAN",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    Modifier.fillMaxWidth().fillMaxHeight().clip(
                        RoundedCornerShape(
                            topStart = 0.dp, topEnd = 15.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    ).background(Color(0xFFEEEEEE)).padding(15.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

//                    Image(
//                        modifier = Modifier.fillMaxWidth(.6f).aspectRatio(1.1f),
//                        imageVector = MyIconPack.Logosmall,
//                        contentDescription = "Small Logo"
//                    )
                    Text(
                        text = "WAITING FOR SCANNER",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffea2a25),
                        textAlign = TextAlign.Center
                    )


                    TextField(
                        modifier = Modifier.fillMaxWidth()
                            .focusRequester(focusRequester),
                        value = homeViewModel.orderNoCheck,
                        singleLine = true,
                        visualTransformation = PrefixTransformation("#"),
                        onValueChange = { homeViewModel.orderNoCheck = it },
                        placeholder = { Text(text = "Enter Order Number") },
                        shape = RoundedCornerShape(5.dp),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            color = LightOnPrimary,
                            fontWeight = FontWeight.Bold
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = LightOnPrimary,
                            disabledTextColor = Color.Transparent,
                            backgroundColor = NeutralWhite,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            placeholderColor = LightOnHint,
                            cursorColor = LightOnSecondary
                        )
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            homeViewModel.createOrder(homeViewModel.orderNoCheck, pickerId)
                            keyboardController?.hide()
                            homeViewModel.orderNoCheck = ""
                            onDismiss()
                        },
                        enabled = homeViewModel.orderNoCheck.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = LightError)
                    ) {
                        Text("Confirm", color = NeutralWhite)
                    }

//                    OutlinedTextField(
//                        modifier = Modifier.fillMaxWidth()
//                            .focusRequester(focusRequester),
//                        value = "${homeViewModel.orderNoCheck}",
//                        visualTransformation = PrefixTransformation("#"),
//                        onValueChange = {
//                            homeViewModel.orderNoCheck = it
//                        },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            imeAction = ImeAction.Done
//                        ),
//                        placeholder = {
//                            Text("Enter Order Number")
//                        },
//                        keyboardActions = KeyboardActions(
//                            onDone = {
//
////                            homeViewModel.createOrder(textFieldValue.text, it)
////                            keyboardController?.hide()
////                            onDismiss()
//                                homeViewModel.isPickingConformDialog = true
//                            }),
//                        textStyle = TextStyle(
//                            textAlign = TextAlign.Center,
//                            fontSize = 24.sp,
//                            color = Colors.text_theme,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        singleLine = true,
//                        maxLines = 1,
//                    )


                }
            }
        }
    }
}

class PrefixTransformation(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix)
    }
}

fun prefixFilter(number: AnnotatedString, prefix: String): TransformedText {

    val out = prefix + number.text
    val prefixOffset = prefix.length

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < prefixOffset) return 0
            return offset - prefixOffset
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}