package com.scanner.binpicking.resoures.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.BtnPlus: ImageVector
    get() {
        if (_btnPlus != null) {
            return _btnPlus!!
        }
        _btnPlus = Builder(name = "BtnPlus", defaultWidth = 26.0.dp, defaultHeight = 26.0.dp,
                viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.0f, 0.0f)
                lineTo(24.0f, 0.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 26.0f, 2.0f)
                lineTo(26.0f, 24.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 24.0f, 26.0f)
                lineTo(2.0f, 26.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 0.0f, 24.0f)
                lineTo(0.0f, 2.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 2.0f, 0.0f)
                close()
            }
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(13.7062f, 12.4401f)
                    verticalLineTo(8.0f)
                    horizontalLineTo(12.4401f)
                    verticalLineTo(12.4401f)
                    horizontalLineTo(8.0f)
                    verticalLineTo(13.7062f)
                    horizontalLineTo(12.4401f)
                    verticalLineTo(18.1463f)
                    horizontalLineTo(13.7062f)
                    verticalLineTo(13.7062f)
                    horizontalLineTo(18.1463f)
                    verticalLineTo(12.4401f)
                    horizontalLineTo(13.7062f)
                    close()
                }
            }
        }
        .build()
        return _btnPlus!!
    }

private var _btnPlus: ImageVector? = null
