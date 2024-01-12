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

val MyIconPack.Showpassword: ImageVector
    get() {
        if (_showpassword != null) {
            return _showpassword!!
        }
        _showpassword = Builder(name = "Showpassword", defaultWidth = 20.0.dp, defaultHeight =
                17.0.dp, viewportWidth = 20.0f, viewportHeight = 17.0f).apply {
            path(fill = SolidColor(Color(0xFF1C202D)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.0f, 11.3333f)
                curveTo(11.4728f, 11.3333f, 12.6667f, 10.1394f, 12.6667f, 8.6667f)
                curveTo(12.6667f, 7.1939f, 11.4728f, 6.0f, 10.0f, 6.0f)
                curveTo(8.5273f, 6.0f, 7.3334f, 7.1939f, 7.3334f, 8.6667f)
                curveTo(7.3334f, 10.1394f, 8.5273f, 11.3333f, 10.0f, 11.3333f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1C202D)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.7842f, 3.8825f)
                curveTo(13.9625f, 2.6333f, 12.0129f, 2.0f, 9.99f, 2.0f)
                curveTo(8.1696f, 2.0f, 6.395f, 2.5417f, 4.7154f, 3.6033f)
                curveTo(3.0217f, 4.6762f, 1.3613f, 6.625f, 0.0f, 8.6667f)
                curveTo(1.1008f, 10.5f, 2.6067f, 12.385f, 4.175f, 13.4658f)
                curveTo(5.9742f, 14.705f, 7.9304f, 15.3333f, 9.99f, 15.3333f)
                curveTo(12.0317f, 15.3333f, 13.9837f, 14.7054f, 15.7942f, 13.4671f)
                curveTo(17.3879f, 12.375f, 18.9046f, 10.4925f, 20.0f, 8.6667f)
                curveTo(18.9008f, 6.8571f, 17.3792f, 4.9767f, 15.7842f, 3.8825f)
                close()
                moveTo(10.0f, 12.6667f)
                curveTo(9.2089f, 12.6667f, 8.4355f, 12.4321f, 7.7777f, 11.9925f)
                curveTo(7.1199f, 11.553f, 6.6072f, 10.9283f, 6.3045f, 10.1974f)
                curveTo(6.0017f, 9.4665f, 5.9225f, 8.6622f, 6.0769f, 7.8863f)
                curveTo(6.2312f, 7.1104f, 6.6122f, 6.3976f, 7.1716f, 5.8382f)
                curveTo(7.731f, 5.2788f, 8.4437f, 4.8979f, 9.2196f, 4.7435f)
                curveTo(9.9956f, 4.5892f, 10.7998f, 4.6684f, 11.5307f, 4.9711f)
                curveTo(12.2616f, 5.2739f, 12.8864f, 5.7866f, 13.3259f, 6.4444f)
                curveTo(13.7654f, 7.1022f, 14.0f, 7.8755f, 14.0f, 8.6667f)
                curveTo(13.9988f, 9.7272f, 13.577f, 10.7439f, 12.8271f, 11.4938f)
                curveTo(12.0772f, 12.2436f, 11.0605f, 12.6655f, 10.0f, 12.6667f)
                close()
            }
        }
        .build()
        return _showpassword!!
    }

private var _showpassword: ImageVector? = null
