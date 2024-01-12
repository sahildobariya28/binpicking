package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.isIos
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.icons.Logosmall
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.utils.ActionType
import com.scanner.binpicking.utils.SoundMediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
inline fun SinglePickingDialog(
    homeViewModel: HomeScreenViewModel,
    crossinline onDismissSinglePickingDialog: () -> Unit,
) {

    LaunchedEffect(Unit) {
        launch(Dispatchers.Main) {
            SoundMediaPlayer().playSound(ActionType.SUCCESS)
        }
    }
    var textFieldValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Dialog(onDismissRequest = {
        keyboardController?.hide()
        focusManager.clearFocus()
        onDismissSinglePickingDialog()
    }) {
        LaunchedEffect(Unit) {
            focusManager.clearFocus()
            focusRequester.requestFocus()
            if (isIos) {
                keyboardController?.show()
            } else {
                keyboardController?.hide()
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(.7f).height(50.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xff07a400)), contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxSize().focusRequester(focusRequester),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                        applicationContext?.getString("picker_id")?.let {
                            homeViewModel.createOrder(textFieldValue, it)
                        }

                    }),
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                singleLine = true,
                maxLines = 1,
            )
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
                ).background(Color(0xffea2a25)).padding(15.dp), contentAlignment = Alignment.Center
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

                Image(
                    modifier = Modifier.fillMaxWidth(.6f).aspectRatio(1.1f),
                    imageVector = MyIconPack.Logosmall,
                    contentDescription = null
                )

                Text(
                    text = "WAITING FOR SCANNER",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffea2a25),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}