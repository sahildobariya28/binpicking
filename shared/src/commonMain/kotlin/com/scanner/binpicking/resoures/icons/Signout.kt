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

public val MyIconPack.Signout: ImageVector
    get() {
        if (_signout != null) {
            return _signout!!
        }
        _signout = Builder(name = "Signout", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.5087f, 17.5794f)
                curveTo(14.8785f, 19.0202f, 12.9456f, 19.7941f, 10.7748f, 19.9658f)
                curveTo(10.7242f, 19.9699f, 10.6746f, 19.9872f, 10.6251f, 19.9981f)
                horizontalLineTo(9.3747f)
                curveTo(9.3314f, 19.9877f, 9.2881f, 19.9736f, 9.2443f, 19.9668f)
                curveTo(8.7877f, 19.9f, 8.3269f, 19.8562f, 7.8755f, 19.7659f)
                curveTo(3.9972f, 18.9879f, 0.7921f, 15.6606f, 0.1566f, 11.7605f)
                curveTo(0.095f, 11.3832f, 0.0517f, 11.0028f, 0.0f, 10.6234f)
                verticalLineTo(9.3736f)
                curveTo(0.0517f, 9.001f, 0.0866f, 8.6248f, 0.1576f, 8.2558f)
                curveTo(0.7321f, 5.2652f, 2.3222f, 2.9738f, 4.9209f, 1.3875f)
                curveTo(5.4881f, 1.041f, 6.1858f, 1.2173f, 6.5261f, 1.7658f)
                curveTo(6.8678f, 2.3168f, 6.7103f, 3.0678f, 6.1482f, 3.4122f)
                curveTo(5.0868f, 4.0634f, 4.2081f, 4.8942f, 3.5443f, 5.9504f)
                curveTo(1.171f, 9.7227f, 2.5523f, 14.749f, 6.5198f, 16.7753f)
                curveTo(11.0519f, 19.0907f, 16.5181f, 16.4272f, 17.4866f, 11.4322f)
                curveTo(18.0888f, 8.3273f, 16.6721f, 5.1332f, 13.9611f, 3.4842f)
                curveTo(13.421f, 3.1554f, 13.2076f, 2.6936f, 13.3313f, 2.1206f)
                curveTo(13.4951f, 1.3598f, 14.3708f, 0.9559f, 15.046f, 1.377f)
                curveTo(15.618f, 1.7334f, 16.1794f, 2.1238f, 16.6799f, 2.5725f)
                curveTo(18.498f, 4.2033f, 19.6048f, 6.2452f, 19.9006f, 8.6665f)
                curveTo(20.3348f, 12.2145f, 19.1899f, 15.2098f, 16.5087f, 17.5794f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.1554f, 8.338f)
                curveTo(11.1544f, 8.9559f, 10.8408f, 9.3937f, 10.3017f, 9.544f)
                curveTo(9.6296f, 9.7313f, 8.9418f, 9.2716f, 8.8594f, 8.5786f)
                curveTo(8.8494f, 8.4946f, 8.8447f, 8.4095f, 8.8447f, 8.3255f)
                curveTo(8.8437f, 5.9694f, 8.8432f, 3.6139f, 8.8447f, 1.2578f)
                curveTo(8.8447f, 0.6019f, 9.1959f, 0.1447f, 9.7835f, 0.0226f)
                curveTo(10.4144f, -0.1078f, 11.051f, 0.3409f, 11.1361f, 0.9791f)
                curveTo(11.1507f, 1.0882f, 11.1554f, 1.1999f, 11.1554f, 1.31f)
                curveTo(11.157f, 2.4685f, 11.1565f, 3.6269f, 11.1565f, 4.7849f)
                curveTo(11.1565f, 5.9694f, 11.158f, 7.154f, 11.1554f, 8.338f)
                close()
            }
        }
        .build()
        return _signout!!
    }

private var _signout: ImageVector? = null
