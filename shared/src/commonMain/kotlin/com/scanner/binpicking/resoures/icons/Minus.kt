package com.scanner.binpicking.resoures.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIconPack.Minus: ImageVector
    get() {
        if (_minus != null) {
            return _minus!!
        }
        _minus = Builder(name = "Minus", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = SolidColor(Color(0xFFF0F2F1)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.6336f, 0.5f)
                lineTo(9.6337f, 0.5f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 18.7676f, 9.6339f)
                lineTo(18.7676f, 9.6339f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 9.6337f, 18.7678f)
                lineTo(9.6336f, 18.7678f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 0.4998f, 9.6339f)
                lineTo(0.4998f, 9.6339f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 9.6336f, 0.5f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF939393)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.4224f, 9.6339f)
                horizontalLineTo(13.4471f)
            }
        }
        .build()
        return _minus!!
    }

private var _minus: ImageVector? = null