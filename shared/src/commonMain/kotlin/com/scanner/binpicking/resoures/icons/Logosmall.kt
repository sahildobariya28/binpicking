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

val MyIconPack.Logosmall: ImageVector
    get() {
        if (_logosmall != null) {
            return _logosmall!!
        }
        _logosmall = Builder(name = "Logosmall", defaultWidth = 69.0.dp, defaultHeight = 69.0.dp,
                viewportWidth = 69.0f, viewportHeight = 69.0f).apply {
            path(fill = SolidColor(Color(0xFFED383A)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(30.1784f, 67.2303f)
                lineTo(1.7624f, 38.7633f)
                curveTo(-0.5875f, 36.4117f, -0.5875f, 32.5883f, 1.7624f, 30.2341f)
                lineTo(30.1784f, 1.7697f)
                curveTo(32.5309f, -0.5899f, 36.3444f, -0.5899f, 38.6942f, 1.7697f)
                lineTo(67.1076f, 30.2314f)
                curveTo(69.4574f, 32.5883f, 69.4574f, 36.409f, 67.1076f, 38.7606f)
                lineTo(38.6942f, 67.2303f)
                curveTo(36.3417f, 69.5899f, 32.5283f, 69.5899f, 30.1784f, 67.2303f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(22.1928f, 32.6815f)
                horizontalLineTo(24.5107f)
                curveTo(27.0761f, 32.6815f, 28.3615f, 31.5643f, 28.3615f, 29.3247f)
                curveTo(28.3615f, 27.0851f, 27.0761f, 25.9653f, 24.5107f, 25.9653f)
                horizontalLineTo(22.1928f)
                verticalLineTo(32.6815f)
                close()
                moveTo(22.1928f, 47.2576f)
                horizontalLineTo(15.2046f)
                verticalLineTo(20.3236f)
                horizontalLineTo(26.3283f)
                curveTo(29.3461f, 20.3236f, 31.6613f, 21.1102f, 33.2634f, 22.6832f)
                curveTo(34.868f, 24.2536f, 35.6691f, 26.4692f, 35.6691f, 29.3274f)
                curveTo(35.6691f, 32.1856f, 34.868f, 34.3985f, 33.2634f, 35.9689f)
                curveTo(31.6613f, 37.542f, 29.3461f, 38.3285f, 26.3283f, 38.3285f)
                horizontalLineTo(22.1928f)
                verticalLineTo(47.2576f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(46.3662f, 20.3213f)
                verticalLineTo(41.3283f)
                horizontalLineTo(54.7462f)
                verticalLineTo(47.2606f)
                horizontalLineTo(39.3779f)
                verticalLineTo(20.3213f)
                horizontalLineTo(46.3662f)
                close()
            }
        }
        .build()
        return _logosmall!!
    }

private var _logosmall: ImageVector? = null
