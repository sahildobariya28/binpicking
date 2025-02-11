package com.scanner.binpicking.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    strokeWidth: Float = 5f,
    dashLength: Float = 10f,
    gapLength: Float = 10f
) {
    Canvas(modifier = modifier) {
        // Define the dashed path effect
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), phase = 0f)
        
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}