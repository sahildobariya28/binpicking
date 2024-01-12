package com.scanner.binpicking.presentation.screen.single_picking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.presentation.component.ShowErrorDialog
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.utils.ActionType
import com.scanner.binpicking.utils.BackHandler
import com.scanner.binpicking.utils.SoundMediaPlayer
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.ktor.http.isSuccess
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SinglePickingScreen(
    dataModel: SinglePickingModel,
    routeHomeScreen: () -> Unit,
    onSuccessFinishOrder: () -> Unit
) {

    val viewModel = getViewModel(
        key = "single-view-model",
        factory = viewModelFactory {
            SinglePickingViewModel(dataModel)
        }
    )

    Box(contentAlignment = Alignment.Center) {

        Column(modifier = Modifier.fillMaxSize().background(Colors.background)) {

            AppBar(onCloseDialog = {
                applicationContext?.getString("picker_id")?.let {
                    viewModel.updateStatus(
                        listOf(viewModel.singlePickingModel.mainRefEntityId.toInt()),
                        viewModel.singlePickingModel.entityId.toInt(),
                        viewModel.singlePickingModel.sku,
                        viewModel.gatherQty,
                        it.toInt()
                    )
                }
            })
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SinglePickingTopComponent(viewModel.singlePickingModel)

                Spacer(Modifier.fillMaxWidth().height(10.dp))

                SinglePickingMiddleComponent(viewModel, viewModel.singlePickingModel, onMissingChanged = {
                    applicationContext?.getString("picker_id")?.let {
                        viewModel.missingProduct(
                            viewModel.gatherQty,
                            viewModel.singlePickingModel.gatherQty.toInt(),
                            viewModel.missingQty,
                            viewModel.singlePickingModel.missing.toInt(),
                            viewModel.singlePickingModel.qty.toInt(),
                            viewModel.singlePickingModel.sku,
                            viewModel.singlePickingModel.entityId.toInt(),
                            viewModel.singlePickingModel.refEntityId.toInt(),
                            it.toInt()
                        )
                    }
                }, onInvalidQtyError = {
                    viewModel.isError = true
                    viewModel.errorMsg = "Total Quantity is grater then required.\n Requested Total Qty : ${viewModel.missingQty + (viewModel.singlePickingModel.gatherQty.toInt() + viewModel.gatherQty)} Required Total Qty : ${viewModel.singlePickingModel.qty.toInt()}"
                })

                Spacer(
                    Modifier.padding(horizontal = 10.dp, vertical = 20.dp).fillMaxWidth()
                        .height(1.5.dp).background(Color(0xFFECECEC))
                )

                SinglePickingBottomComponent(viewModel, viewModel.singlePickingModel, {
                    viewModel.gatherQty++

                    if ((viewModel.gatherQty + viewModel.singlePickingModel.missing.toInt()) == viewModel.singlePickingModel.qty.toInt()) {

                        applicationContext?.getString("picker_id")?.let {
                            viewModel.scanOrder(
                                viewModel.singlePickingModel.sku,
                                viewModel.singlePickingModel.entityId.toInt(),
                                viewModel.gatherQty,
                                it.toInt()
                            )
                        }
                    } else {
                        GlobalScope.launch(Dispatchers.Main) {
                            SoundMediaPlayer().playSound(ActionType.SUCCESS)
                        }
                    }
                }, {
                    viewModel.isError = true
                    viewModel.errorMsg = it
                })
            }
        }
//        LaunchedEffect(viewModel.gatherQty) {
//            // Load audio asynchronously
//            withContext(Dispatchers.IO) {
//                playClickSound(HttpRoutes.ERROR_AUDIO)
//            }
//        }

        val scanProductState by viewModel.scannedProductDetail.collectAsState()
        val updateStatusState by viewModel.updateStatus.collectAsState()
        val missingProductState by viewModel.missingProductDetail.collectAsState()
        val skipProductState by viewModel.skipProductDetail.collectAsState()
        val previousProductState by viewModel.previousProductDetail.collectAsState()

        LaunchedEffect(updateStatusState) {

            if (updateStatusState.isLoading) {
                viewModel.isLoading = true
            } else if (updateStatusState.status.isSuccess()) {
                updateStatusState.data?.let { model ->
                    viewModel.isLoading = false
                    if ((model as SimpleResponse).isSuccess) {
                        routeHomeScreen()
                    } else {
                        viewModel.errorMsg = model.message
                        viewModel.isError = true
                    }


                }
            } else if (!updateStatusState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }

        LaunchedEffect(scanProductState) {
            if (scanProductState.isLoading) {
                viewModel.isLoading = true
            } else if (scanProductState.status.isSuccess()) {
                scanProductState.data?.let { model ->
                    if ((model as SinglePickingModel).isSuccess) {

                        SoundMediaPlayer().playSound(ActionType.SUCCESS)

                        if (model.proccessFinish) {
                            viewModel.gatherQty = 0
                            viewModel.missingQty = 0
                            onSuccessFinishOrder()
                        } else if (model.isChangeProduct == 1 || model.isChangeProduct == -1) {
                            viewModel.gatherQty = 0
                            viewModel.missingQty = 0
                            viewModel.singlePickingModel = model
                            viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                            viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                        } else if (model.isChangeProduct == 0) {
                            var newSinglePickingModel = viewModel.singlePickingModel
                            newSinglePickingModel.ordertotalqty = model.ordertotalqty
                            newSinglePickingModel.ordergetqty = model.ordergetqty
                            newSinglePickingModel.missing = model.missing
                            newSinglePickingModel.credit = model.credit
                            newSinglePickingModel.gatherQty = model.gatherQty

                            viewModel.singlePickingModel = newSinglePickingModel
                            viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                            viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                        }
                        viewModel.isLoading = false
                    } else {
                        viewModel.errorMsg = model.errorMsg
                        viewModel.isError = true
                    }


                }
            } else if (!scanProductState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }
        LaunchedEffect(missingProductState) {
            if (missingProductState.isLoading) {
                viewModel.isLoading = true
            } else if (missingProductState.status.isSuccess()) {
                missingProductState.data?.let { model ->
                    viewModel.isLoading = false

                    if ((model as SinglePickingModel).isSuccess) {

                        GlobalScope.launch(Dispatchers.Main) {
                            SoundMediaPlayer().playSound(ActionType.SUCCESS)
                        }
                        if (model.proccessFinish) {
                            viewModel.gatherQty = 0
                            viewModel.missingQty = 0
                            onSuccessFinishOrder()
                        } else if (model.isChangeProduct == 1 || model.isChangeProduct == -1) {
                            viewModel.gatherQty = 0
                            viewModel.missingQty = 0
                            viewModel.singlePickingModel = model
                            viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                            viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                        } else if (model.isChangeProduct == 0) {
                            var newSinglePickingModel = viewModel.singlePickingModel
                            newSinglePickingModel.ordertotalqty = model.ordertotalqty
                            newSinglePickingModel.ordergetqty = model.ordergetqty
                            newSinglePickingModel.missing = model.missing
                            newSinglePickingModel.credit = model.credit
                            newSinglePickingModel.gatherQty = model.gatherQty

                            viewModel.singlePickingModel = newSinglePickingModel
                            viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                            viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                        }
                    } else {
                        viewModel.errorMsg = model.errorMsg
                        viewModel.isError = true
                    }


                }
            } else if (!missingProductState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }

        LaunchedEffect(skipProductState) {

            if (skipProductState.isLoading) {
                viewModel.isLoading = true
            } else if (skipProductState.status.isSuccess()) {
                skipProductState.data?.let { model ->
                    viewModel.isLoading = false
                    if ((model as SinglePickingModel).isSuccess) {

                        GlobalScope.launch(Dispatchers.Main) {
                            SoundMediaPlayer().playSound(ActionType.SUCCESS)
                        }
                        viewModel.singlePickingModel = model
                        viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                        viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                    } else {
                        viewModel.errorMsg = model.errorMsg
                        viewModel.isError = true
                    }


                }
            } else if (!skipProductState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }
        LaunchedEffect(previousProductState) {
            if (previousProductState.isLoading) {
                viewModel.isLoading = true
            } else if (previousProductState.status.isSuccess()) {
                previousProductState.data?.let { model ->
                    viewModel.isLoading = false
                    if ((model as SinglePickingModel).isSuccess) {

                        GlobalScope.launch(Dispatchers.Main) {
                            SoundMediaPlayer().playSound(ActionType.SUCCESS)
                        }
                        viewModel.singlePickingModel = model
                        viewModel.gatherQty = viewModel.singlePickingModel.gatherQty.toInt()
                        viewModel.missingQty = viewModel.singlePickingModel.missing.toInt()
                        viewModel.isLoading = false
                    } else {
                        viewModel.errorMsg = model.errorMsg
                        viewModel.isError = true
                    }
                }
            } else if (!previousProductState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }


        if (viewModel.isLoading) {
            ShowLoadingDialog()
        }

        if (viewModel.isError) {
            ShowErrorDialog(viewModel.errorMsg) {
                viewModel.isError = false
                viewModel.errorMsg = ""
            }
        }

    }
    BackHandler(true) {
        applicationContext?.getString("picker_id")?.let {
            viewModel.updateStatus(
                listOf(viewModel.singlePickingModel.mainRefEntityId.toInt()),
                viewModel.singlePickingModel.entityId.toInt(),
                viewModel.singlePickingModel.sku,
                viewModel.gatherQty,
                it.toInt()
            )
        }
    }
}