package com.scanner.binpicking.util

import androidx.compose.runtime.Composable

@Composable
actual fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit) {
    content.invoke(0f, 0f)
}