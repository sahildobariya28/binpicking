package com.scanner.binpicking.presentation.screen.picking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.img_logo_horizontal
import com.scanner.binpicking.presentation.utils.pxToDp
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.topSafeArea
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppBar(version: String, onBackPress: () -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(pxToDp(topSafeArea) + 70.dp)
            .background(LightPrimary)
            .padding(top = topSafeArea.dp),
        contentAlignment = Alignment.CenterStart,
    ) {

        Icon(
            modifier = Modifier
                .size(70.dp)
                .clickable { onBackPress() }
                .padding(15.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(Res.drawable.img_logo_horizontal),
                contentDescription = "logo",
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 15.dp),
            text = version,
            color = Color.White
        )
    }
}