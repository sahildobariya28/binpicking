package com.scanner.binpicking.presentation.screen.application_container

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class ApplicationScreenState(): Parcelable {

    object SplashScreen: ApplicationScreenState()
    object SingInScreen: ApplicationScreenState()
    object HomeScreen: ApplicationScreenState()
    object SinglePickingScreen: ApplicationScreenState()
    object MultiPickingScreen: ApplicationScreenState()
    data class ItemListScreen(var entityId: String,var pickerName: String): ApplicationScreenState()
}