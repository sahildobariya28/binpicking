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
import com.scanner.binpicking.bottomSafeArea
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.Logobiglight
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.topSafeArea
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi


@Composable
inline fun SplashScreen(crossinline signInRoute: () -> Unit) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Colors.theme)
                .padding(top = topSafeArea.dp, bottom = bottomSafeArea.dp),
            contentAlignment = Alignment.Center
        ) {

//            ImageLoader("images/logo.png").value?.let {
                Image(
                    modifier = Modifier.fillMaxWidth(.6f),
                    imageVector = MyIconPack.Logobiglight,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
//            }
        }
        LaunchedEffect(true) {
            delay(3000)
            signInRoute()
        }
    }
}