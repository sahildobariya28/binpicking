package com.scanner.binpicking.presentation.screen.homescreen.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

enum class ExpandedType {
    HALF, FULL, COLLAPSED
}

@Composable
fun BottomSheetGestureWrapper(
    modifier: Modifier = Modifier,
    onExpandTypeChanged: (ExpandedType) -> Unit,
    content: @Composable () -> Unit
) {

    var expandedType by remember {
        mutableStateOf(ExpandedType.COLLAPSED)
    }

    var isUpdated = false

    LaunchedEffect(key1 = expandedType) {
        onExpandTypeChanged(expandedType)
    }

    Box(
        modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { change, dragAmount ->
                        change.consume()
                        if (!isUpdated) {
                            expandedType = when {
                                dragAmount < 0 && expandedType == ExpandedType.COLLAPSED -> {
                                    ExpandedType.HALF
                                }

                                dragAmount < 0 && expandedType == ExpandedType.HALF -> {
                                    ExpandedType.FULL
                                }

                                dragAmount > 0 && expandedType == ExpandedType.FULL -> {
                                    ExpandedType.HALF
                                }

                                dragAmount > 0 && expandedType == ExpandedType.HALF -> {
                                    ExpandedType.COLLAPSED
                                }

                                else -> {
                                    expandedType
                                }
                            }
                            isUpdated = true
                        }
                    },
                    onDragEnd = {
                        isUpdated = false
                    }
                )
            }
            .background(Color.White)
    ) {
        content()
    }
}