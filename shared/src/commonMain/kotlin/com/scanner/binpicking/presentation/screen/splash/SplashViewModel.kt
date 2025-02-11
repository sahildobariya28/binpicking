package com.scanner.binpicking.presentation.screen.splash

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SplashViewModel(
    componentContext: ComponentContext,
    val onNavigateToAuthScreen: () -> Unit,
    val onNavigateToMainScreen: () -> Unit
) : ViewModel() {


}