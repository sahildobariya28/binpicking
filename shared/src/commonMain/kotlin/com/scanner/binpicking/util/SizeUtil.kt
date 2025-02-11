package com.scanner.binpicking.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun toDp(value: Int): Dp {
    val screenPixelDensity = LocalDensity.current
    val dpVal = value / screenPixelDensity.density
    return dpVal.dp
}

@Composable
fun toPx(dp: Dp): Float {
    val screenPixelDensity = LocalDensity.current
    return dp.value * screenPixelDensity.density
}
