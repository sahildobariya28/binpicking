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

public val MyIconPack.BtnPrevious: ImageVector
    get() {
        if (_btnPrevious != null) {
            return _btnPrevious!!
        }
        _btnPrevious = Builder(name = "BtnPrevious", defaultWidth = 17.0.dp, defaultHeight =
                17.0.dp, viewportWidth = 17.0f, viewportHeight = 17.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF333333)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.4112f, 9.5702f)
                    curveTo(2.9351f, 11.262f, 5.4604f, 12.9516f, 7.9857f, 14.6407f)
                    curveTo(8.4249f, 14.9344f, 8.9221f, 14.8641f, 9.1606f, 14.4677f)
                    curveTo(9.2326f, 14.3475f, 9.2563f, 14.2013f, 9.2868f, 14.113f)
                    curveTo(9.1998f, 13.5196f, 9.1224f, 12.9826f, 9.0421f, 12.4465f)
                    curveTo(8.9642f, 11.9271f, 8.8786f, 11.4087f, 8.8094f, 10.8883f)
                    curveTo(8.7973f, 10.7977f, 8.8181f, 10.6561f, 8.8791f, 10.6168f)
                    curveTo(8.9371f, 10.5794f, 9.0842f, 10.6201f, 9.164f, 10.6686f)
                    curveTo(11.3546f, 11.9956f, 13.5413f, 13.3272f, 15.728f, 14.6592f)
                    curveTo(15.9331f, 14.7841f, 16.1435f, 14.8632f, 16.3936f, 14.8086f)
                    curveTo(16.7849f, 14.7226f, 17.049f, 14.3817f, 16.9933f, 14.0001f)
                    curveTo(16.7936f, 12.6297f, 16.5996f, 11.2583f, 16.3791f, 9.8907f)
                    curveTo(16.263f, 9.1678f, 16.2964f, 8.4559f, 16.4091f, 7.7381f)
                    curveTo(16.6107f, 6.4496f, 16.7965f, 5.1587f, 16.9895f, 3.8687f)
                    curveTo(17.0369f, 3.551f, 16.9227f, 3.2975f, 16.6446f, 3.1241f)
                    curveTo(16.3708f, 2.9534f, 16.0835f, 2.9631f, 15.8088f, 3.1268f)
                    curveTo(15.3174f, 3.4196f, 14.8303f, 3.7184f, 14.3418f, 4.0158f)
                    curveTo(12.6175f, 5.0652f, 10.8931f, 6.1151f, 9.1683f, 7.1641f)
                    curveTo(9.1127f, 7.1974f, 9.0547f, 7.2363f, 8.9928f, 7.2488f)
                    curveTo(8.8559f, 7.277f, 8.7736f, 7.1729f, 8.7988f, 6.9962f)
                    curveTo(8.8593f, 6.5675f, 8.925f, 6.1397f, 8.9894f, 5.7118f)
                    curveTo(9.0798f, 5.1124f, 9.1712f, 4.5135f, 9.2617f, 3.914f)
                    curveTo(9.3129f, 3.5755f, 9.2152f, 3.291f, 8.8984f, 3.1106f)
                    curveTo(8.5893f, 2.9349f, 8.289f, 2.9853f, 8.0012f, 3.1777f)
                    curveTo(5.472f, 4.8724f, 2.9414f, 6.5647f, 0.4127f, 8.2598f)
                    curveTo(-0.1358f, 8.6275f, -0.1368f, 9.2029f, 0.4112f, 9.5702f)
                    close()
                }
            }
        }
        .build()
        return _btnPrevious!!
    }

private var _btnPrevious: ImageVector? = null
