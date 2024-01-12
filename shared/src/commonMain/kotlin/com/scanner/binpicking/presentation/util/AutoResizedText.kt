package com.scanner.binpicking.presentation.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp

@Composable
fun SmartText(
    text: String,
    style: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Medium,
    downScale: Float = 60f,
    textAlign: TextAlign = TextAlign.Center,
    alignment: Alignment = Alignment.Center,
    isAutoSetWidth: Boolean = false
) {

    var parentSize: IntSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier.onSizeChanged { parentSize = it }, contentAlignment = alignment) {

        var resizedTextStyle by remember {
            mutableStateOf(style)
        }
        var shouldDraw by remember {
            mutableStateOf(false)
        }

        val defaultFontSize = (toDp(parentSize.height, downScale).value.sp).value.sp
        Text(
            modifier = if (isAutoSetWidth) {
                Modifier.drawWithContent {
                    if (shouldDraw) {
                        drawContent()
                    }
                }
            } else {
                Modifier
            },
            text = text,
            fontWeight = fontWeight,
            color = color,
            softWrap = false,
            textAlign = textAlign,
            style = style.copy(fontSize = defaultFontSize),
            onTextLayout = { result ->

                if (result.didOverflowWidth) {
                    if (isAutoSetWidth) {
                        resizedTextStyle = resizedTextStyle.copy(
                            fontSize = resizedTextStyle.fontSize * 0.95
                        )
                    }
                } else {
                    shouldDraw = true
                }
            }

        )

    }


}

@Composable
fun SmartText(
    text: AnnotatedString,
    style: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier.fillMaxSize(),
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Medium,
    downScale: Float = 60f,
    textAlign: TextAlign = TextAlign.Center,
    alignment: Alignment = Alignment.Center,
    isAutoSetWidth: Boolean = false
) {

    var parentSize: IntSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier.onSizeChanged { parentSize = it }, contentAlignment = alignment) {

        var resizedTextStyle by remember {
            mutableStateOf(style)
        }
        var shouldDraw by remember {
            mutableStateOf(false)
        }

        val defaultFontSize = (toDp(parentSize.height, downScale).value.sp).value.sp
        Text(
            modifier = if (isAutoSetWidth) {
                Modifier.drawWithContent {
                    if (shouldDraw) {
                        drawContent()
                    }
                }
            } else {
                Modifier
            },
            text = text,
            fontWeight = fontWeight,
            color = color,
            softWrap = false,
            textAlign = textAlign,
            style = style.copy(fontSize = defaultFontSize),
            onTextLayout = { result ->
                if (isAutoSetWidth) {
                    if (result.didOverflowWidth) {
                        if (isAutoSetWidth) {
                            resizedTextStyle = resizedTextStyle.copy(
                                fontSize = resizedTextStyle.fontSize * 0.95
                            )
                        }
                    } else {
                        shouldDraw = true
                    }
                }
            }
        )
    }
}


@Composable
fun SmartBasicTextField(
    text: String,
    hint: String = "",
    onValueChange: (String) -> Unit,
    style: TextStyle = MaterialTheme.typography.h4,
    modifier: Modifier = Modifier,
    color: Color = style.color,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
    alignment: Alignment = Alignment.Center,
    downScale: Float = 60f,
    isAutoSetWidth: Boolean = false
) {

    var parentSize: IntSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier.onSizeChanged { parentSize = it }, contentAlignment = alignment) {

        var textFieldValue by remember {
            mutableStateOf(text)
        }

        var resizedTextStyle by remember {
            mutableStateOf(style)
        }
        var shouldDraw by remember {
            mutableStateOf(false)
        }

        val defaultFontSize = (toDp(parentSize.height, downScale).value.sp).value.sp
        BasicTextField(
            modifier = if (isAutoSetWidth) {
                Modifier.drawWithContent {
                    if (shouldDraw) {
                        drawContent()
                    }
                }
            } else {
                Modifier.fillMaxSize()
            },
            value = textFieldValue,
            onValueChange = {
                onValueChange(it)
                textFieldValue = it
            },
            textStyle = TextStyle(
                textAlign = textAlign,
                fontSize = defaultFontSize,
                color = color,
                fontWeight = fontWeight
            ),
            singleLine = true,
            maxLines = 1,
            onTextLayout = { result ->
                if (isAutoSetWidth) {
                    if (result.didOverflowHeight) {
                        if (style.fontSize.isUnspecified) {
                            resizedTextStyle = resizedTextStyle.copy(
                                fontSize = defaultFontSize
                            )
                        }
                        resizedTextStyle = resizedTextStyle.copy(
                            fontSize = resizedTextStyle.fontSize * 0.95
                        )
                    } else {
                        shouldDraw = true
                    }
                }
            }
        )
    }
}

