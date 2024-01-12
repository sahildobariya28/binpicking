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

val MyIconPack.Plus: ImageVector
    get() {
        if (_plus != null) {
            return _plus!!
        }
        _plus = Builder(name = "Plus", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = SolidColor(Color(0xFFF0F2F1)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.3663f, 0.5f)
                lineTo(10.3663f, 0.5f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 19.5002f, 9.6339f)
                lineTo(19.5002f, 9.6339f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 10.3663f, 18.7678f)
                lineTo(10.3663f, 18.7678f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 1.2324f, 9.6339f)
                lineTo(1.2324f, 9.6339f)
                arcTo(9.1339f, 9.1339f, 0.0f, false, true, 10.3663f, 0.5f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF939393)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.155f, 9.9349f)
                horizontalLineTo(14.1797f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF939393)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.6672f, 6.4226f)
                lineTo(10.6672f, 13.4473f)
            }
        }
        .build()
        return _plus!!
    }

private var _plus: ImageVector? = null