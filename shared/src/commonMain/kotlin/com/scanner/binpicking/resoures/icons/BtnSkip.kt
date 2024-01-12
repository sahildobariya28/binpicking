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

public val MyIconPack.BtnSkip: ImageVector
    get() {
        if (_btnSkip != null) {
            return _btnSkip!!
        }
        _btnSkip = Builder(name = "BtnSkip", defaultWidth = 17.0.dp, defaultHeight = 17.0.dp,
                viewportWidth = 17.0f, viewportHeight = 17.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.5895f, 9.5723f)
                curveTo(14.0656f, 11.2647f, 11.5403f, 12.9548f, 9.015f, 14.6445f)
                curveTo(8.5758f, 14.9383f, 8.0786f, 14.868f, 7.8401f, 14.4715f)
                curveTo(7.7681f, 14.3512f, 7.7444f, 14.205f, 7.7139f, 14.1166f)
                curveTo(7.801f, 13.523f, 7.8784f, 12.9858f, 7.9587f, 12.4496f)
                curveTo(8.0365f, 11.93f, 8.1221f, 11.4114f, 8.1913f, 10.8909f)
                curveTo(8.2034f, 10.8002f, 8.1826f, 10.6586f, 8.1216f, 10.6193f)
                curveTo(8.0636f, 10.5818f, 7.9166f, 10.6225f, 7.8368f, 10.6711f)
                curveTo(5.6462f, 11.9985f, 3.4594f, 13.3305f, 1.2727f, 14.663f)
                curveTo(1.0676f, 14.7879f, 0.8572f, 14.867f, 0.6072f, 14.8124f)
                curveTo(0.2159f, 14.7264f, -0.0482f, 14.3854f, 0.0074f, 14.0037f)
                curveTo(0.2072f, 12.6328f, 0.4011f, 11.261f, 0.6217f, 9.8929f)
                curveTo(0.7378f, 9.1698f, 0.7044f, 8.4577f, 0.5917f, 7.7397f)
                curveTo(0.39f, 6.4507f, 0.2042f, 5.1594f, 0.0113f, 3.869f)
                curveTo(-0.0361f, 3.5511f, 0.078f, 3.2976f, 0.3561f, 3.1241f)
                curveTo(0.6299f, 2.9534f, 0.9172f, 2.9631f, 1.1919f, 3.1269f)
                curveTo(1.6833f, 3.4197f, 2.1704f, 3.7186f, 2.6589f, 4.0161f)
                curveTo(4.3833f, 5.0659f, 6.1076f, 6.1162f, 7.8324f, 7.1655f)
                curveTo(7.888f, 7.1988f, 7.9461f, 7.2377f, 8.008f, 7.2502f)
                curveTo(8.1449f, 7.2784f, 8.2271f, 7.1743f, 8.2019f, 6.9975f)
                curveTo(8.1415f, 6.5686f, 8.0757f, 6.1407f, 8.0114f, 5.7127f)
                curveTo(7.9209f, 5.1131f, 7.8295f, 4.5139f, 7.7391f, 3.9143f)
                curveTo(7.6878f, 3.5757f, 7.7855f, 3.2911f, 8.1023f, 3.1107f)
                curveTo(8.4114f, 2.9349f, 8.7117f, 2.9853f, 8.9995f, 3.1778f)
                curveTo(11.5287f, 4.873f, 14.0593f, 6.5659f, 16.588f, 8.2615f)
                curveTo(17.1365f, 8.6294f, 17.1375f, 9.2049f, 16.5895f, 9.5723f)
                close()
            }
        }
        .build()
        return _btnSkip!!
    }

private var _btnSkip: ImageVector? = null
