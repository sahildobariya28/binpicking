package com.scanner.binpicking.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.scanner.binpicking.theme.Colors.LightBackground
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.LightPrimaryVariant
import com.scanner.binpicking.theme.Colors.LightSecondary

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Automatically detect system theme
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        MaterialTheme.colors.copy(
            primary = LightPrimary,
            primaryVariant = LightPrimaryVariant,
            secondary = LightSecondary,
            background = LightBackground,
            surface = Color.LightGray
        )
    } else {
        MaterialTheme.colors.copy(
            primary = LightPrimary,
            primaryVariant = LightPrimaryVariant,
            secondary = LightSecondary,
            background = LightBackground,
            surface = Color.LightGray
        )
    }

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        shapes = MaterialTheme.shapes, // You can define custom shapes too
        content = content
    )
}
