package com.scanner.binpicking.presentation.screen.picking

import ContentWithMessageBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightBackground
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.NeutralWhite
import rememberMessageBarState


@Composable
fun NewPickingScreen(viewModel: PickingViewModel) {

    viewModel.messageBarState = rememberMessageBarState()

    viewModel.keyboardController = LocalSoftwareKeyboardController.current!!
    viewModel.focusRequester = remember { FocusRequester() }
    viewModel.focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.showKeyboard()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        AppBar(version = "5.7", onBackPress = {
            AppConfig.getPickerId()?.let { pickerId ->
                viewModel.updateStatus(pickerId.toInt())
            }
        })

        ContentWithMessageBar(
            messageBarState = viewModel.messageBarState,
            successContainerColor = Colors.LightSuccessPrimary,
            successContentColor = NeutralWhite,
            errorContainerColor = Red,
            errorContentColor = NeutralWhite
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                NewPickingTopComponent(viewModel.pickingModel)

                Spacer(modifier = Modifier.height(10.dp))

                NewPickingMiddleComponent(
                    viewModel = viewModel,
                    pickingModel = viewModel.pickingModel,
                    onMissingChanged = {
                        AppConfig.getPickerId()?.let { pickerId ->
                            viewModel.missingProduct(
                                pickerId = pickerId.toInt()
                            )
                        }
                    },
                    onInvalidQtyError = {
                        viewModel.messageBarState.addError(exception = Exception("Total Quantity is greater than required.\nRequested Total Qty: ${viewModel.uiMissingQty + (viewModel.pickingModel.data.gatherQty + viewModel.uiGatherQty)}. Required Total Qty: ${viewModel.pickingModel.data.qty.toInt()}"))
                    }
                )

                Spacer(modifier = Modifier.fillMaxWidth().height(1.5.dp))

                NewPickingBottomComponent(
                    viewModel = viewModel,
                    pickingModel = viewModel.pickingModel,
                    onGatherQtyChanged = {
                        viewModel.uiGatherQty++
                        if ((viewModel.pickingModel.data.gatherQty + viewModel.uiGatherQty + viewModel.pickingModel.data.missing) == viewModel.pickingModel.data.qty.toInt()) {
                            AppConfig.getPickerId()?.let { pickerId ->
                                viewModel.scanOrder(picker = pickerId.toInt())
                            }
                        } else {
                            viewModel.playSuccessSound()
                        }
                    },
                    onSkuNotMatched = {
                        viewModel.playErrorSound()
                        viewModel.messageBarState.addError(exception = Exception(it))
                    }
                )
            }
        }
    }
}