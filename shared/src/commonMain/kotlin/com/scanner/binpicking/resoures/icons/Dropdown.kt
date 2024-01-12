package com.scanner.binpicking.resoures.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.Dropdown: ImageVector
    get() {
        if (_dropdown != null) {
            return _dropdown!!
        }
        _dropdown = Builder(name = "Dropdown", defaultWidth = 12.0.dp, defaultHeight = 7.0.dp,
                viewportWidth = 12.0f, viewportHeight = 7.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 0.8f, strokeLineWidth = 1.5f, strokeLineCap = Round,
                    strokeLineJoin = StrokeJoin.Companion.Round, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(1.0f, 1.0f)
                lineTo(6.0f, 6.0f)
                lineTo(11.0f, 1.0f)
            }
        }
        .build()
        return _dropdown!!
    }

private var _dropdown: ImageVector? = null
