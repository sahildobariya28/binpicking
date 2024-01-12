package com.scanner.binpicking.presentation.screen.application_container

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreen
import com.scanner.binpicking.presentation.screen.itemlistscreen.ItemListScreen
import com.scanner.binpicking.presentation.screen.multi_picking.MultiPickingContainer
import com.scanner.binpicking.presentation.screen.signinscreen.SignInScreen
import com.scanner.binpicking.presentation.screen.single_picking.SinglePickingScreen
import com.scanner.binpicking.presentation.screen.splash.SplashScreen
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import com.arkivanov.decompose.router.stack.replaceCurrent
import io.github.xxfast.decompose.router.rememberRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ApplicationContainer() {

    Box(Modifier.fillMaxSize()) {
        val router: Router<ApplicationScreenState> = rememberRouter(
            ApplicationScreenState::class, listOf(
                ApplicationScreenState.SplashScreen
            ))

        var singlePickingModel by mutableStateOf<SinglePickingModel?>(null)

        RoutedContent(
            router = router,
            animation = stackAnimation(slide(animationSpec = tween(100)))
        ) { screen ->

            when (screen) {
                is ApplicationScreenState.SplashScreen -> {
                    loadScreen(SplashScreen(
                        signInRoute = {
                            if (applicationContext?.getString("login_token") != null && applicationContext?.getString(
                                    "login_token"
                                )!!.isNotEmpty()
                            ) {
                                router.replaceCurrent(ApplicationScreenState.HomeScreen)
                            } else {
                                router.replaceCurrent(ApplicationScreenState.SingInScreen)
                            }
                        }
                    ))
                }

                is ApplicationScreenState.SingInScreen -> {

                    SignInScreen {
                        router.replaceCurrent(ApplicationScreenState.HomeScreen)
                    }
                }

                is ApplicationScreenState.HomeScreen -> {
                    HomeScreen(routeSinglePickingScreen = { model ->
                        singlePickingModel = model
                        router.push(ApplicationScreenState.SinglePickingScreen)
                    }, routeSingInScreen = {
                        router.replaceCurrent(ApplicationScreenState.SingInScreen)
                    }, routeItemListScreen = {entityId, pickerName ->
                        router.push(ApplicationScreenState.ItemListScreen(entityId, pickerName))
                    })
                }

                is ApplicationScreenState.MultiPickingScreen -> {
                    loadScreen(MultiPickingContainer())
                }

                is ApplicationScreenState.SinglePickingScreen -> {
                    singlePickingModel?.let {
                        loadScreen(SinglePickingScreen(
                            dataModel = it,
                            routeHomeScreen = {
                                router.pop()
                            }, onSuccessFinishOrder = {
                                router.pop()
                            }))
                    }
                }
                is ApplicationScreenState.ItemListScreen -> {
                    loadScreen(ItemListScreen(screen.entityId, screen.pickerName){
                        router.pop()
                    })
                }
            }
        }
    }
}

@Composable
fun loadScreen(homeScreen: Unit) {
    val isReady = remember { mutableStateOf(false) }
    if (isReady.value) {
        Box(Modifier.fillMaxSize()) {
            homeScreen
        }
    }
    rememberCoroutineScope().launch {
        delay(100)
        isReady.value = true
    }
}