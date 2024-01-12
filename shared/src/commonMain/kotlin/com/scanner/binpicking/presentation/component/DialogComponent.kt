package com.scanner.binpicking.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.Error
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.utils.ActionType
import com.scanner.binpicking.utils.SoundMediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
inline fun ShowLoadingDialog() {
    Dialog(onDismissRequest = {

    }) {

        Column(
            Modifier.fillMaxWidth(.8f).clip(RoundedCornerShape(15.dp)).background(Color.White)
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.padding(vertical = 20.dp))
            Text(
                "Please Wait...",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.text_theme
            )
        }
    }
}

@Composable
inline fun ShowErrorDialog(errorMsg: String, crossinline onErrorDialogClose: () -> Unit) {

    Dialog(onDismissRequest = {
        onErrorDialogClose()
    }) {
        LaunchedEffect(Unit) {
            launch(Dispatchers.Main) {
                SoundMediaPlayer().playSound(ActionType.ERROR)
            }
        }


        Column(
            Modifier.fillMaxWidth(.8f).clip(RoundedCornerShape(15.dp)).background(Color.White)
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.size(120.dp),
                imageVector = MyIconPack.Error,
                contentDescription = null
            )

            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = errorMsg,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Colors.red,
                textAlign = TextAlign.Center
            )
        }
    }
}