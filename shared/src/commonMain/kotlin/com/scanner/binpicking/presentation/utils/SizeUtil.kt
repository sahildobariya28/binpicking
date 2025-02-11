package com.scanner.binpicking.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun toDp(value: Int, downScale:Float): Dp {
    val screenPixelDensity = LocalDensity.current
    val dpVal = value / screenPixelDensity.density
    val finalDp = ((dpVal * downScale) / 100)
    return finalDp.dp
}


@Composable
fun dpToPx(dp: Dp): Float {
    val density = LocalDensity.current.density
    return dp.value * density
}
@Composable
fun dpToSp(dp: Dp): TextUnit {
    return pxToSp(dpToPx(dp))
}
@Composable
fun pxToDp(px: Float): Dp {
    val density = LocalDensity.current.density
    return (px / density).dp
}
@Composable
fun pxToSp(px: Float): TextUnit {
    val scaledDensity = LocalDensity.current.fontScale
    return (px / scaledDensity).sp
}
@Composable
fun spToPx(sp: TextUnit): Float {
    val scaledDensity = LocalDensity.current.fontScale
    return (sp.value * scaledDensity)
}

@Composable
fun spToDp(sp: TextUnit): Dp {
    return pxToDp(spToPx(sp))
}




