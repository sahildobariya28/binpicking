package com.scanner.binpicking.presentation.screen.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.domain.mode.SimpleResponse
import com.scanner.binpicking.domain.mode.OrderList
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.presentation.component.ShowErrorDialog
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.screen.homescreen.component.FilterDialog
import com.scanner.binpicking.presentation.screen.homescreen.component.ProductView
import com.scanner.binpicking.presentation.screen.homescreen.component.SinglePickingDialog
import com.scanner.binpicking.presentation.screen.homescreen.component.TopBarUi
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.topSafeArea
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.ktor.http.isSuccess


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    routeSinglePickingScreen: (singlePickingModel: SinglePickingModel) -> Unit,
    routeSingInScreen: () -> Unit,
    routeItemListScreen: (entityId: String, pickerName: String) -> Unit
) {
    val viewModel = getViewModel(key = "home-view-model", factory = viewModelFactory {
        HomeScreenViewModel()
    })

//    val coroutineScope = rememberCoroutineScope()
//    val multiState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmStateChange = { true },
//        skipHalfExpanded = false,
//    )
//
//    val multiCountState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmStateChange = { true },
//        skipHalfExpanded = false,
//    )

//    ModalBottomSheetLayout(
//        sheetState = multiState,
//        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
//        sheetContent = {
//            CreateMultiPickingDialog(viewModel = viewModel,
//                onDismissMultiPickingDialog = {
//                    coroutineScope.launch {
//                        if (multiState.isVisible)
//                            multiState.hide()
//                        else
//                            multiState.show()
//                    }
//                }
//            ) {
//
//            }
//        },
//    ) {
//        ModalBottomSheetLayout(
//            sheetState = multiCountState,
//            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
//            sheetContent = {
//                SelectMultiPickingCountDialog(viewModel, {
//                    coroutineScope.launch {
//                        if (multiCountState.isVisible)
//                            multiCountState.hide()
//                        else
//                            multiCountState.show()
//                    }
//                }, {
//                    viewModel.multiOrderList.clear()
//                    for (i in 0 until it) {
//                        viewModel.multiOrderList.add(MultiPickingData())
//                        if (i == (it - 1)) {
//                            coroutineScope.launch {
//                                multiCountState.hide()
//
//                                if (multiState.isVisible)
//                                    multiState.hide()
//                                else
//                                    multiState.show()
//                            }
//                        }
//                    }
//
//
//                })
//            }
//        ) {


    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(Colors.background)) {
            TopBarUi(viewModel = viewModel, onFilterClickListener = {
                viewModel.isFilter = !viewModel.isFilter
            }, onSignOutClickListener = {
                viewModel.isSignOutDialog = !viewModel.isSignOutDialog
            }, onApplyFilter = { filter ->
                viewModel.orderListWithApplyFilter(filter)
            })


            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Colors.white)
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row {
                        Box(
                            Modifier.clip(RoundedCornerShape(8.dp)).background(
                                if (viewModel.isSelectionMode) Color(0xff1A86D0) else Color(
                                    0xffEB5016
                                )
                            ).clickable {
                                if (viewModel.isSelectionMode) {
                                    //call processing to pending api
                                    viewModel.updateStatus(viewModel.selectedOrderList.map { it.entityId.toInt() })
                                } else {
                                    viewModel.isSinglePickingDialog =
                                        !viewModel.isSinglePickingDialog
                                }


                            }.padding(horizontal = 30.dp, vertical = 15.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (viewModel.isSelectionMode) "Move to Pending" else "Single Picking",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        if (viewModel.isSelectionMode) {
                            Box(
                                Modifier.clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (viewModel.isSelectionMode) Colors.red else Color(
                                            0xffEB5016
                                        )
                                    )
                                    .clickable {
                                        if (viewModel.isSelectionMode) {
                                            //call delete pick api

                                            viewModel.deleteOrder(viewModel.selectedOrderList.map { it.entityId.toInt() })

                                        } else {
//                                                coroutineScope.launch {
//                                                    if (modalSheetCountState.isVisible)
//                                                        modalSheetCountState.hide()
//                                                    else
//                                                        modalSheetCountState.show()
//                                                }
                                        }

                                    }.padding(horizontal = 15.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = if (viewModel.isSelectionMode) "Delete Pick" else "Multi Picking",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp
                                )
                            }


                        }

                    }

                }


            }

            val orderListState by viewModel.orderList.collectAsState()

            LaunchedEffect(orderListState) {
                if (orderListState.isLoading) {
                    viewModel.isLoading = true
                } else if (orderListState.status.isSuccess()) {

                    (orderListState.data as OrderList).let { newData ->
                        viewModel.isLoading = false

                        if (newData.data.isEmpty()) {
                            viewModel.itemFinish = true
                        } else if (newData.data.size < viewModel.pageSize) {
                            viewModel.itemFinish = true
                            viewModel.orderItemList.addAll(newData.data)
                            viewModel.selectedOrderList.clear()
                            viewModel.isSelectionMode = false
                        } else {
                            viewModel.itemFinish = false
                            viewModel.orderItemList.addAll(newData.data)
                            viewModel.selectedOrderList.clear()
                            viewModel.isSelectionMode = false
                            viewModel.isMoreDataRequest = false
                        }
                    }
                } else if (!orderListState.status.isSuccess()) {
                    viewModel.isLoading = false
                }
            }


            val pullRefreshState = rememberPullRefreshState(viewModel.isRefreshing, {
                viewModel.refreshOrderList()
            })
            Box(Modifier.pullRefresh(pullRefreshState)) {
                val lazyListState = rememberLazyListState()

                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    state = lazyListState
                ) {


                    items(count = viewModel.orderItemList.size,
                        key = {
                            viewModel.orderItemList[it].entityId
                        }) { pos ->

                        ProductView(
                            viewModel,
                            viewModel.orderItemList[pos]
                        ) { entityId, pickerName ->
                            routeItemListScreen(entityId, pickerName)
                        }


                        if (pos == viewModel.orderItemList.size - 1 && viewModel.orderItemList.isNotEmpty()) {
                            if (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == viewModel.orderItemList.size - 1 && !viewModel.isMoreDataRequest) {
                                if (!viewModel.itemFinish) {

                                    viewModel.loadMoreProducts(!viewModel.isMoreDataRequest)
                                }
                            }
                        }
                    }


                    if (viewModel.isMoreDataRequest) {
                        item {
                            if (viewModel.itemFinish) {
                                PaginationExhaust()
                            } else {
                                PaginationLoading()
                            }
                        }
                    }

                }
                PullRefreshIndicator(
                    viewModel.isRefreshing,
                    pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }

        if (viewModel.isFilter) {
            Box(
                Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) { viewModel.isFilter = !viewModel.isFilter }
            )
        }
        AnimatedVisibility(
            visible = viewModel.isFilter,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300)),
            exit = slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
                Box(
                    Modifier.padding(top = (topSafeArea + 70).dp).fillMaxHeight()
                        .fillMaxWidth(.9f)
                        .background(Color.White)
                ) {
                    FilterDialog(viewModel = viewModel, onDismissFilterDialog = {
                        viewModel.isFilter = !viewModel.isFilter
                    }, onApplyFilter = { filter ->
                        viewModel.orderListWithApplyFilter(filter)
                        viewModel.isFilter = !viewModel.isFilter
                    })
                }
            }
        }

        val createOrderState by viewModel.orderDetail.collectAsState()
        val updateStatusState by viewModel.updateStatus.collectAsState()
        val orderDeleteState by viewModel.orderDelete.collectAsState()

        LaunchedEffect(updateStatusState) {

            if (updateStatusState.isLoading) {
                viewModel.isLoading = true
            } else if (updateStatusState.status.isSuccess()) {
                updateStatusState.data?.let { model ->
                    viewModel.isLoading = false
                    if ((model as SimpleResponse).isSuccess) {
                        viewModel.refreshOrderList()
                    } else {
                        viewModel.errorMsg = model.message
                        viewModel.isError = true
                    }


                }
            } else if (!updateStatusState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }

        LaunchedEffect(createOrderState) {
            if (createOrderState.isLoading) {
                viewModel.isLoading = true
            } else if (createOrderState.status.isSuccess()) {
                createOrderState.data?.let { model ->
                    viewModel.isLoading = false

                    if ((model as SinglePickingModel).isSuccess) {
                        routeSinglePickingScreen((model))
                    } else {
                        viewModel.errorMsg = model.errorMsg
                        viewModel.isError = true
                    }

                }
            } else if (!createOrderState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }

        LaunchedEffect(orderDeleteState) {
            if (orderDeleteState.isLoading) {
                viewModel.isLoading = true
            } else if (orderDeleteState.status.isSuccess()) {
                orderDeleteState.data?.let { model ->
                    viewModel.isLoading = false

                    if ((model as SimpleResponse).isSuccess) {

                        viewModel.orderItemList.removeAll(viewModel.selectedOrderList)
                        viewModel.selectedOrderList.clear()
                        viewModel.isSelectionMode = false
                    } else {
                        viewModel.errorMsg = model.message
                        viewModel.isError = true
                    }

                }
            } else if (!orderDeleteState.status.isSuccess()) {
                viewModel.isLoading = false
            }
        }

        if (viewModel.isSinglePickingDialog) {
            LocalFocusManager.current.clearFocus()
            SinglePickingDialog(viewModel) {
                viewModel.isSinglePickingDialog = !viewModel.isSinglePickingDialog
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
        if (viewModel.isSignOutDialog) {

            ConfirmSignOut(onConfirmSignOutListener = {
                applicationContext?.put("login_token", "")
                applicationContext?.put("picker_id", "")
                routeSingInScreen()
            }, onCloseSignOutDialog = {
                viewModel.isSignOutDialog = false
            })
        }
    }
//        }
//    }
}

@Composable
fun ConfirmSignOut(onConfirmSignOutListener: () -> Unit, onCloseSignOutDialog: () -> Unit) {

    Dialog(onDismissRequest = {
        onCloseSignOutDialog()
    }) {
        Column(
            Modifier.width(IntrinsicSize.Max).clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(modifier = Modifier.size(40.dp).clickable { onCloseSignOutDialog() }
                    .padding(5.dp), imageVector = Icons.Rounded.Close, contentDescription = null)

                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    text = "SignOut",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Colors.text_theme,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.fillMaxWidth().height(20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = "Are you sure.\nYou want to SignOut your Account ?..",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Colors.text_hint,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.fillMaxWidth().height(20.dp))

            Row(
                Modifier.padding(horizontal = 15.dp).fillMaxWidth().height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    Modifier.fillMaxSize().weight(1f).clip(RoundedCornerShape(10.dp))
                        .background(Colors.theme).clickable {
                            onConfirmSignOutListener()
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(text = "Confirm", color = Colors.white)
                }
            }
        }
    }

}

@Composable
fun PaginationLoading() {
    Box(
        Modifier.fillMaxWidth().height(80.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
    }
}


@Composable
fun PaginationExhaust() {
    Box(
        Modifier.fillMaxWidth().height(80.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Thank You")
    }
}







