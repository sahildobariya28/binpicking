package com.scanner.binpicking.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.scanner.binpicking.presentation.screen.authentication.EmailConformationScreenViewModel
import com.scanner.binpicking.presentation.screen.home.HomeViewModel
import com.scanner.binpicking.presentation.screen.orderitem.OrderItemsViewModel
import com.scanner.binpicking.presentation.screen.authentication.SignInScreenViewModel
import com.scanner.binpicking.presentation.screen.authentication.SignUpScreenViewModel
import com.scanner.binpicking.presentation.screen.picking.PickingViewModel
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.presentation.screen.splash.SplashViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.serialization.Serializable

class RootComponent(componentContext: ComponentContext) : ComponentContext by componentContext,
    BackHandlerOwner {
    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.SplashScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    fun onBackClicked() {
        navigation.pop()
    }

    private fun createChild(config: Configuration, context: ComponentContext): Child {
        return when (config) {

            is Configuration.SplashScreen -> Child.SplashScreen(
                viewModelFactory {
                    SplashViewModel(componentContext = context,
                        onNavigateToAuthScreen = {
                            navigation.replaceCurrent(Configuration.SignInScreen)
                        }, onNavigateToMainScreen = {
                            navigation.replaceCurrent(Configuration.HomeScreen)
                        })
                }.createViewModel()
            )

            Configuration.SignInScreen -> Child.SignInScreen(
                viewModelFactory {
                    SignInScreenViewModel(context, {
                        navigation.replaceCurrent(Configuration.HomeScreen)
                    }, {
                        navigation.bringToFront(Configuration.SignUpScreen)
                    })
                }.createViewModel()
            )

            Configuration.SignUpScreen -> Child.SignUpScreen(
                viewModelFactory {
                    SignUpScreenViewModel(context) {
                        navigation.bringToFront(Configuration.EmailConformationScreen)
                    }
                }.createViewModel()
            )

            Configuration.EmailConformationScreen -> Child.EmailConformationScreen(
                viewModelFactory {
                    EmailConformationScreenViewModel(context) {
                        navigation.bringToFront(Configuration.SignInScreen)
                    }
                }.createViewModel()
            )

            Configuration.HomeScreen -> Child.HomeScreen(
                viewModelFactory {
                    HomeViewModel(context, { pickingModel ->
                        navigation.bringToFront(Configuration.Picking(pickingModel))
                    }, {
                        navigation.replaceCurrent(Configuration.SignInScreen)
                    }, { entityId, pickerName ->
                        navigation.bringToFront(Configuration.ItemListScreen(entityId, pickerName))
                    })
                }.createViewModel()
            )

            is Configuration.ItemListScreen -> Child.ItemListScreen(
                viewModelFactory {
                    OrderItemsViewModel(
                        entityId = config.entityId,
                        pickerName = config.pickerName,
                        onBackPressed = {
                            navigation.pop()
                        }, {
                            navigation.replaceCurrent(Configuration.SignInScreen)
                        })
                }.createViewModel()
            )

            is Configuration.Picking -> Child.Picking(
                viewModelFactory {
//                    PickingViewModel(
//                        context = context,
//                        dataModel = config.dataModel,
//                        onBackPressed = {
//                            navigation.replaceCurrent(Configuration.HomeScreen)
//                        }, {
//                            navigation.replaceCurrent(Configuration.SignInScreen)
//                        })
                    PickingViewModel(
                        context = context,
                        dataModel = config.dataModel,
                        onBackPressed = {
                            navigation.pop()
                        }, {
                            navigation.replaceCurrent(Configuration.SignInScreen)
                        })
                }.createViewModel()
            )
        }

    }


    sealed class Child {

        data class SplashScreen(val component: SplashViewModel) : Child()

        data class SignInScreen(val component: SignInScreenViewModel) : Child()

        data class SignUpScreen(val component: SignUpScreenViewModel) : Child()

        data class EmailConformationScreen(val component: EmailConformationScreenViewModel) : Child()

        data class HomeScreen(val component: HomeViewModel) : Child()

        data class ItemListScreen(val component: OrderItemsViewModel) : Child()

        data class Picking(val component: PickingViewModel) : Child()

    }

    @Serializable
    sealed class Configuration {

        @Serializable
        data object SplashScreen : Configuration()

        @Serializable
        data object SignInScreen : Configuration()

        @Serializable
        data object SignUpScreen : Configuration()

        @Serializable
        data object EmailConformationScreen : Configuration()

        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data class ItemListScreen(val entityId: String, val pickerName: String) : Configuration()

        @Serializable
        data class Picking(val dataModel: PickingModelWrapper) : Configuration()

    }
}