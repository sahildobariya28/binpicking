package com.scanner.binpicking.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.theme.Colors.LightOnPrimary

@Composable
fun ShowLoadingDialog() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)), contentAlignment = Alignment.Center) {
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
                color = LightOnPrimary
            )
        }
    }
}