package com.scanner.binpicking.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.bottomSafeArea
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.topSafeArea
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.img_logo_horizontal
import com.scanner.binpicking.core.AppConfig


@Composable
fun SplashScreen(viewModel: SplashViewModel) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(LightPrimary)
                .padding(top = topSafeArea.dp, bottom = bottomSafeArea.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(.6f),
                painter = painterResource(Res.drawable.img_logo_horizontal),
                contentDescription = "Horizontal Logo",
                contentScale = ContentScale.Fit
            )
        }
        LaunchedEffect(true) {
            delay(3000)

            if (AppConfig.getToken().isNullOrEmpty()){
                viewModel.onNavigateToAuthScreen()
            }else{
                viewModel.onNavigateToMainScreen()
            }
        }
    }
}