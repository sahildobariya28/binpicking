package com.scanner.binpicking.navigation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveback.predictiveBackAnimatable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimator
import com.arkivanov.essenty.backhandler.BackEvent
import com.arkivanov.essenty.backhandler.BackHandler

@OptIn(ExperimentalDecomposeApi::class)
internal actual fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T> =
    predictiveBackAnimation(
        backHandler = backHandler,
        animation = stackAnimation(fade() + scale()),
        selector = { initialBackEvent, _, _ ->
            predictiveBackAnimatable(
                initialBackEvent = initialBackEvent,
                enterModifier = { progress, _ -> Modifier.enterModifier(progress) },
                exitModifier = { progress, edge -> Modifier.exitModifier(progress, edge) },
            )
        },
        onBack = onBack,
    )

private fun scale(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    frontFactor: Float = 1.15F,
): StackAnimator =
    stackAnimator(animationSpec = animationSpec) { factor, _, content ->
        content(
            Modifier.scale(
                if (factor >= 0F) {
                    factor * (1f - frontFactor) + 1F
                } else {
                    1F
                }
            )
        )
    }

private fun Modifier.exitModifier(progress: Float, edge: BackEvent.SwipeEdge): Modifier =
    scale(1F - progress * 0.5F)
        .absoluteOffset(
            x =
            when (edge) {
                BackEvent.SwipeEdge.LEFT -> 32.dp * progress
                BackEvent.SwipeEdge.RIGHT -> (-32).dp * progress
                BackEvent.SwipeEdge.UNKNOWN -> 0.dp
            },
        )
        .alpha(((1F - progress) * 2F).coerceAtMost(1F))
        .clip(RoundedCornerShape(size = 64.dp * progress))

private fun Modifier.enterModifier(progress: Float): Modifier = drawWithContent {
    drawContent()
    drawRect(color = Color(red = 0F, green = 0F, blue = 0F, alpha = (1F - progress) / 4F))
}
