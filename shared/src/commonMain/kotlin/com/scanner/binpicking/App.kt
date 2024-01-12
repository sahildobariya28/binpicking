package com.scanner.binpicking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import com.scanner.binpicking.presentation.screen.application_container.ApplicationContainer
import com.scanner.binpicking.utils.SafeArea
import com.scanner.binpicking.states.Action
import com.scanner.binpicking.states.createStore
import com.scanner.binpicking.utils.KMMPreference
import io.github.xxfast.decompose.LocalComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

var applicationContext: KMMPreference? = null
val store = CoroutineScope(SupervisorJob()).createStore()
var topSafeArea: Float = 0f
var bottomSafeArea: Float = 0f
var isIos: Boolean = false
var screenSize = Pair(-1, -1)

@Composable
fun App(pref: KMMPreference, isIosChecked: Boolean) {
    isIos = isIosChecked
    applicationContext = pref


    SafeArea{ top, bottom ->
        topSafeArea = top
        bottomSafeArea = bottom

        MaterialTheme {
            Box(Modifier.fillMaxSize().background(Color.White).onSizeChanged { screenSize = Pair(it.width, it.height) }) {
                ApplicationContainer()
            }
        }

    }
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}