package com.scanner.binpicking

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.scanner.binpicking.navigation.RootComponent
import com.scanner.binpicking.navigation.backAnimation
import com.scanner.binpicking.presentation.screen.authentication.EmailConformationScreen
import com.scanner.binpicking.presentation.screen.authentication.SignInScreen
import com.scanner.binpicking.presentation.screen.authentication.SignUpScreen
import com.scanner.binpicking.presentation.screen.home.HomeScreen
import com.scanner.binpicking.presentation.screen.picking.NewPickingScreen
import com.scanner.binpicking.presentation.screen.orderitem.ItemListScreen
import com.scanner.binpicking.presentation.screen.splash.SplashScreen
import com.scanner.binpicking.theme.AppTheme
import com.scanner.binpicking.util.BrowserWrapper
import com.scanner.binpicking.util.KMMPreference
import com.scanner.binpicking.util.beepSound

var topSafeArea: Float = 0f
var bottomSafeArea: Float = 0f
var applicationContext: KMMPreference? = null
lateinit var browserWrapper: BrowserWrapper
var isIos: Boolean = false

@Composable
fun Application(
    root: RootComponent,
    browser: BrowserWrapper,
    context: KMMPreference,
    isIosChecked: Boolean
) {
    browserWrapper = browser
    isIos = isIosChecked
    applicationContext = context
    beepSound()
    val childStake by root.childStack.subscribeAsState()

    AppTheme {
        Scaffold {
            Children(
                stack = childStake,
                animation = backAnimation(
                    backHandler = root.backHandler,
                    onBack = root::onBackClicked,
                ),
            ) { child ->
                when (val instance = child.instance) {
                    is RootComponent.Child.SplashScreen -> SplashScreen(instance.component)
                    is RootComponent.Child.SignInScreen -> SignInScreen(instance.component)
                    is RootComponent.Child.SignUpScreen -> SignUpScreen(instance.component)
                    is RootComponent.Child.EmailConformationScreen -> EmailConformationScreen(instance.component)
                    is RootComponent.Child.HomeScreen -> HomeScreen(instance.component)
                    is RootComponent.Child.ItemListScreen -> ItemListScreen(instance.component)
                    is RootComponent.Child.Picking -> NewPickingScreen(instance.component)
                }
            }
        }
    }
}

