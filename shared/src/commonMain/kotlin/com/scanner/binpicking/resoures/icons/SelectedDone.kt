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

public val MyIconPack.SelectedDone: ImageVector
    get() {
        if (_selectedDone != null) {
            return _selectedDone!!
        }
        _selectedDone = Builder(name = "SelectedDone", defaultWidth = 18.0.dp, defaultHeight =
                18.0.dp, viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            path(fill = SolidColor(Color(0xFF02BB7D)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(9.0f, 9.0f)
                moveToRelative(-9.0f, 0.0f)
                arcToRelative(9.0f, 9.0f, 0.0f, true, true, 18.0f, 0.0f)
                arcToRelative(9.0f, 9.0f, 0.0f, true, true, -18.0f, 0.0f)
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(13.0879f, 7.193f)
                curveTo(11.8104f, 8.6534f, 10.5324f, 10.1143f, 9.2548f, 11.5748f)
                curveTo(8.943f, 11.9305f, 8.6308f, 12.2858f, 8.3204f, 12.6424f)
                curveTo(8.1775f, 12.8062f, 8.009f, 12.912f, 7.7856f, 12.8989f)
                curveTo(7.5975f, 12.9076f, 7.4438f, 12.8384f, 7.3145f, 12.7091f)
                curveTo(6.6148f, 12.0093f, 5.9133f, 11.3113f, 5.2157f, 10.609f)
                curveTo(4.9327f, 10.3242f, 4.9296f, 9.9245f, 5.1979f, 9.6458f)
                curveTo(5.4757f, 9.358f, 5.8772f, 9.344f, 6.1728f, 9.6231f)
                curveTo(6.6714f, 10.093f, 7.1673f, 10.5654f, 7.6563f, 11.0453f)
                curveTo(7.7591f, 11.1454f, 7.8061f, 11.1467f, 7.9015f, 11.037f)
                curveTo(9.2922f, 9.4368f, 10.6891f, 7.8418f, 12.0803f, 6.242f)
                curveTo(12.2758f, 6.0177f, 12.5075f, 5.9485f, 12.7814f, 6.0378f)
                curveTo(13.0492f, 6.1249f, 13.2599f, 6.2842f, 13.2869f, 6.5908f)
                curveTo(13.3061f, 6.815f, 13.239f, 7.0201f, 13.0879f, 7.193f)
                close()
            }
        }
        .build()
        return _selectedDone!!
    }

private var _selectedDone: ImageVector? = null
