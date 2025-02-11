package com.scanner.binpicking

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.window.ComposeUIViewController
import co.touchlab.crashkios.crashlytics.enableCrashlytics
import co.touchlab.crashkios.crashlytics.setCrashlyticsUnhandledExceptionHook
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.scanner.binpicking.navigation.RootComponent
import com.scanner.binpicking.util.BrowserWrapper
import com.scanner.binpicking.util.KMMPreference
import platform.UIKit.UIViewController
import platform.AudioToolbox.AudioServicesPlaySystemSound
import platform.AudioToolbox.kSystemSoundID_Vibrate

typealias HomeViewController = (backDispatcher: BackDispatcher) -> UIViewController

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController(browserWrapper: BrowserWrapper, context: KMMPreference) = ComposeUIViewController() {

    val lifecycle = LifecycleRegistry()
    val backDispatcher = BackDispatcher()

    val componentContext =
        DefaultComponentContext(
            lifecycle = lifecycle,
            backHandler = backDispatcher, // Pass BackDispatcher here
        )

    val root = remember {
        RootComponent(componentContext)
    }
    AudioServicesPlaySystemSound(kSystemSoundID_Vibrate)
    setupCrashlytics()
    PredictiveBackGestureOverlay(
        backDispatcher = backDispatcher, // Use the same BackDispatcher as above
        backIcon = { progress, _ ->
            PredictiveBackGestureIcon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                progress = progress,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Application(root, browserWrapper, context, true)
    }

}
fun setupCrashlytics() {
    enableCrashlytics()
    setCrashlyticsUnhandledExceptionHook()
}
