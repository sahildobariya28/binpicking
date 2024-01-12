package com.scanner.binpicking.resoures.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.BtnMinus: ImageVector
    get() {
        if (_btnMinus != null) {
            return _btnMinus!!
        }
        _btnMinus = Builder(name = "BtnMinus", defaultWidth = 19.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 19.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.0f, 0.0938f)
                lineTo(17.0f, 0.0938f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 19.0f, 2.0938f)
                lineTo(19.0f, 17.0938f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 17.0f, 19.0938f)
                lineTo(2.0f, 19.0938f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 0.0f, 17.0938f)
                lineTo(0.0f, 2.0938f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 2.0f, 0.0938f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(13.2608f, 8.8633f)
                horizontalLineTo(5.8462f)
                verticalLineTo(9.7901f)
                horizontalLineTo(13.2608f)
                verticalLineTo(8.8633f)
                close()
            }
        }
        .build()
        return _btnMinus!!
    }

private var _btnMinus: ImageVector? = null
