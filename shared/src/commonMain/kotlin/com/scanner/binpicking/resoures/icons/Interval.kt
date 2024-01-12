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

public val MyIconPack.Interval: ImageVector
    get() {
        if (_interval != null) {
            return _interval!!
        }
        _interval = Builder(name = "Interval", defaultWidth = 15.0.dp, defaultHeight = 17.0.dp,
                viewportWidth = 15.0f, viewportHeight = 17.0f).apply {
            path(fill = SolidColor(Color(0xFF6B7280)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.3321f, 5.1638f)
                curveTo(12.3034f, 5.131f, 12.2759f, 5.0973f, 12.2396f, 5.054f)
                curveTo(12.5752f, 4.7197f, 12.9081f, 4.389f, 13.23f, 4.0689f)
                curveTo(12.8944f, 3.7356f, 12.5668f, 3.4102f, 12.2378f, 3.083f)
                curveTo(11.9036f, 3.4173f, 11.5463f, 3.7754f, 11.1966f, 4.1256f)
                curveTo(10.8787f, 3.9414f, 10.5941f, 3.7621f, 10.297f, 3.6072f)
                curveTo(9.5546f, 3.2203f, 8.7644f, 2.9843f, 7.933f, 2.8825f)
                curveTo(7.8834f, 2.8767f, 7.8006f, 2.8201f, 7.7997f, 2.7855f)
                curveTo(7.7908f, 2.3384f, 7.7935f, 1.8917f, 7.7935f, 1.4242f)
                horizontalLineTo(9.2128f)
                verticalLineTo(0.0f)
                horizontalLineTo(4.9624f)
                verticalLineTo(1.4127f)
                horizontalLineTo(6.3817f)
                verticalLineTo(1.8395f)
                curveTo(6.3817f, 2.1383f, 6.3804f, 2.4371f, 6.3826f, 2.7359f)
                curveTo(6.3835f, 2.8152f, 6.3768f, 2.8732f, 6.2759f, 2.8842f)
                curveTo(5.0863f, 3.0153f, 4.007f, 3.439f, 3.0277f, 4.1154f)
                curveTo(2.6674f, 3.7542f, 2.3097f, 3.3951f, 1.9644f, 3.0489f)
                curveTo(1.6128f, 3.3982f, 1.2848f, 3.7241f, 0.9541f, 4.053f)
                curveTo(1.2737f, 4.37f, 1.6102f, 4.7038f, 1.9126f, 5.0039f)
                curveTo(1.5637f, 5.5095f, 1.2016f, 5.9597f, 0.9191f, 6.4556f)
                curveTo(-1.4556f, 10.6272f, 0.9944f, 15.9455f, 5.707f, 16.8685f)
                curveTo(5.9771f, 16.9212f, 6.2511f, 16.9566f, 6.5229f, 17.0f)
                horizontalLineTo(7.6523f)
                curveTo(7.9728f, 16.9464f, 8.2969f, 16.9075f, 8.6138f, 16.8375f)
                curveTo(11.4339f, 16.2168f, 13.5965f, 13.9621f, 14.0627f, 11.1142f)
                curveTo(14.431f, 8.8648f, 13.8334f, 6.8784f, 12.3321f, 5.1638f)
                close()
                moveTo(7.0577f, 15.5798f)
                curveTo(3.9172f, 15.5612f, 1.3893f, 12.9997f, 1.4181f, 9.8662f)
                curveTo(1.4468f, 6.7261f, 4.0123f, 4.2234f, 7.1741f, 4.25f)
                curveTo(10.243f, 4.2761f, 12.7815f, 6.8558f, 12.7567f, 9.9237f)
                curveTo(12.7319f, 13.0754f, 10.1894f, 15.5988f, 7.0577f, 15.5798f)
                close()
            }
            path(fill = SolidColor(Color(0xFF6B7280)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.3484f, 12.6407f)
                curveTo(9.1841f, 14.0445f, 7.218f, 14.5461f, 5.5198f, 13.8665f)
                curveTo(3.6741f, 13.1281f, 2.8179f, 11.3772f, 2.8463f, 9.9193f)
                horizontalLineTo(7.0812f)
                verticalLineTo(5.7012f)
                curveTo(8.0857f, 5.4382f, 9.8982f, 6.4184f, 10.6707f, 7.6301f)
                curveTo(11.6677f, 9.1933f, 11.5384f, 11.2054f, 10.3484f, 12.6407f)
                close()
            }
        }
        .build()
        return _interval!!
    }

private var _interval: ImageVector? = null
