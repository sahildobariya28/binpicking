package com.scanner.binpicking.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.scanner.binpicking.store


@Composable
actual fun BackHandler(isEnabled: Boolean, onBack: () -> Unit) {
    LaunchedEffect(isEnabled) {
        store.events.collect {
            if(isEnabled) {
                onBack()
            }
        }
    }
}