package com.scanner.binpicking.utils

import androidx.compose.runtime.Composable

@Composable
expect fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit)