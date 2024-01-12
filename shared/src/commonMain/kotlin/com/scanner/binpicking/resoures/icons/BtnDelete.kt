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

public val MyIconPack.BtnDelete: ImageVector
    get() {
        if (_btnDelete != null) {
            return _btnDelete!!
        }
        _btnDelete = Builder(name = "BtnDelete", defaultWidth = 42.0.dp, defaultHeight = 42.0.dp,
                viewportWidth = 42.0f, viewportHeight = 42.0f).apply {
            path(fill = SolidColor(Color(0xFFF9E4E4)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.0f, 0.0f)
                lineTo(32.0f, 0.0f)
                arcTo(10.0f, 10.0f, 0.0f, false, true, 42.0f, 10.0f)
                lineTo(42.0f, 32.0f)
                arcTo(10.0f, 10.0f, 0.0f, false, true, 32.0f, 42.0f)
                lineTo(10.0f, 42.0f)
                arcTo(10.0f, 10.0f, 0.0f, false, true, 0.0f, 32.0f)
                lineTo(0.0f, 10.0f)
                arcTo(10.0f, 10.0f, 0.0f, false, true, 10.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEC0000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(14.3334f, 16.8385f)
                horizontalLineTo(27.6667f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEC0000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(19.3333f, 20.168f)
                verticalLineTo(25.168f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEC0000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(22.6667f, 20.168f)
                verticalLineTo(25.168f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEC0000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(15.1667f, 16.832f)
                lineTo(16.0001f, 26.832f)
                curveTo(16.0001f, 27.7525f, 16.7463f, 28.4987f, 17.6667f, 28.4987f)
                horizontalLineTo(24.3334f)
                curveTo(25.2539f, 28.4987f, 26.0001f, 27.7525f, 26.0001f, 26.832f)
                lineTo(26.8334f, 16.832f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFEC0000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(18.5f, 16.8333f)
                verticalLineTo(14.3333f)
                curveTo(18.5f, 13.8731f, 18.8731f, 13.5f, 19.3333f, 13.5f)
                horizontalLineTo(22.6667f)
                curveTo(23.1269f, 13.5f, 23.5f, 13.8731f, 23.5f, 14.3333f)
                verticalLineTo(16.8333f)
            }
        }
        .build()
        return _btnDelete!!
    }

private var _btnDelete: ImageVector? = null
