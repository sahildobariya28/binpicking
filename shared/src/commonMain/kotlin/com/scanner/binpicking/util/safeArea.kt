package com.scanner.binpicking.util

import androidx.compose.runtime.Composable

@Composable
expect fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit)