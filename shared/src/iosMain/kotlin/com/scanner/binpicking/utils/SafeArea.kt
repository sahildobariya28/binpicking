package com.scanner.binpicking.utils

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGFloat
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit) {
    val statusBarHeight = UIApplication.sharedApplication.statusBarFrame.useContents { size.height }
    val window = UIApplication.sharedApplication.keyWindow!!
    val paddingTop: CGFloat
    val paddingBottom: CGFloat
    if (window.respondsToSelector(NSSelectorFromString("safeAreaInsets"))) {
        paddingTop = window.safeAreaInsets.useContents { top }
        paddingBottom = window.safeAreaInsets.useContents { bottom }
    } else {
        paddingTop = statusBarHeight
        paddingBottom = 0.0
    }
    content.invoke((paddingTop).toFloat(), paddingBottom.toFloat())
}