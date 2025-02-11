package com.scanner.binpicking.presentation.screen.home

import ContentWithMessageBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.screen.home.component.FilterSideBar
import com.scanner.binpicking.presentation.screen.home.component.ProductView
import com.scanner.binpicking.presentation.screen.home.component.SignOutConfirmationDialog
import com.scanner.binpicking.presentation.screen.home.component.PickingConformDialog
import com.scanner.binpicking.presentation.screen.home.component.PickingDialog
import com.scanner.binpicking.presentation.screen.home.component.TopBarUi
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightBackground
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.NeutralWhite
import kotlinx.datetime.Clock
import rememberMessageBarState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val scaffoldState = rememberScaffoldState()

    viewModel.messageBarState = rememberMessageBarState()

    LaunchedEffect(Unit) {
        viewModel.orderList.data = emptyList()
        viewModel.getOrderList(viewModel.pageSize, viewModel.currentPage, viewModel.lastFilter)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarUi(viewModel = viewModel, onFilterClickListener = {
                viewModel.isFilterSlider = !viewModel.isFilterSlider
            }, onSignOutClickListener = {
                viewModel.isSignOutDialog = !viewModel.isSignOutDialog
            }, onApplyFilter = { filter ->
                viewModel.orderListWithApplyFilter(filter)
            })
        },
    ) {

        ContentWithMessageBar(
            messageBarState = viewModel.messageBarState,
            position = MessageBarPosition.BOTTOM,
            successContainerColor = Colors.LightSuccessPrimary,
            successContentColor = NeutralWhite,
            errorContainerColor = Red,
            errorContentColor = NeutralWhite
        ) {

            Column(modifier = Modifier.fillMaxSize().background(LightBackground)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(NeutralWhite)
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            if (viewModel.isSelectionMode) {
                                viewModel.updateStatus(viewModel.selectedOrderList.map { it.entityId.toInt() })
                            } else {
                                viewModel.playSuccessSound()
                                viewModel.isPickingDialog = !viewModel.isPickingDialog
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (viewModel.isSelectionMode) {
                                Color(0xff1A86D0)
                            } else {
                                Color(0xffEB5016)
                            }
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
                    ) {
                        Text(
                            text = if (viewModel.isSelectionMode) "Move to Pending" else "Single Picking",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(Modifier.width(10.dp))

                    // Second Button: Visible only if in Selection Mode (for "Delete Pick")
//                    if (viewModel.isSelectionMode) {
//                        Button(
//                            onClick = {
//                                viewModel.deleteOrder(viewModel.selectedOrderList.map { it.entityId.toInt() })
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                backgroundColor = LightError
//                            ),
//                            shape = RoundedCornerShape(8.dp),
//                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
//                        ) {
//                            Text(
//                                text = "Delete Pick",
//                                color = Color.White,
//                                textAlign = TextAlign.Center,
//                                fontSize = 16.sp
//                            )
//                        }
//                    }
                }

                val pullRefreshState =
                    rememberPullRefreshState(viewModel.isRefreshing, {
                        if (!viewModel.isSelectionMode) {
                            viewModel.refreshOrderList()
                        }
                    })


                if (viewModel.orderList.data.isNotEmpty()) {
                    Box(Modifier.pullRefresh(pullRefreshState)) {


                        val lazyListState = rememberLazyListState()
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(vertical = 10.dp),
                            state = lazyListState
                        ) {

                            items(count = viewModel.orderList.data.size,
                                key = {
                                    "${viewModel.orderList.data[it].entityId}_${
                                        Clock.System.now().toEpochMilliseconds()
                                    }"
                                }) { pos ->
                                if (viewModel.orderList.data.isNotEmpty()) {
                                    ProductView(
                                        viewModel,
                                        viewModel.orderList.data[pos]
                                    ) { entityId, pickerName ->
                                        viewModel.onNavigateToItemListScreen(
                                            entityId,
                                            pickerName
                                        )
                                    }
                                }

                                if (pos == viewModel.orderList.data.size - 1 && viewModel.orderList.data.isNotEmpty()) {
                                    if (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == viewModel.orderList.data.size - 1 && !viewModel.isMoreDataRequest) {
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

                        if (!viewModel.isSelectionMode) {
                            PullRefreshIndicator(
                                viewModel.isRefreshing,
                                pullRefreshState,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    }
                } else {

                    val pullRefreshState2 =
                        rememberPullRefreshState(viewModel.isRefreshing, {
                            if (!viewModel.isSelectionMode) {
                                viewModel.refreshOrderList()
                            }
                        })
                    Box(
                        Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                            .pullRefresh(pullRefreshState2), contentAlignment = Alignment.Center
                    ) {
                        Text("No Data Found")

                        PullRefreshIndicator(
                            viewModel.isRefreshing,
                            pullRefreshState2,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }

            if (viewModel.isLoading) {
                ShowLoadingDialog()
            }


            if (viewModel.isFilterSlider) {
                Box(
                    Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() } // This is mandatory
                        ) { viewModel.isFilterSlider = !viewModel.isFilterSlider }
                )
            }
            AnimatedVisibility(
                visible = viewModel.isFilterSlider,
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
                        Modifier.fillMaxHeight()
                            .fillMaxWidth(.9f)
                            .background(Color.White)
                    ) {
                        FilterSideBar(viewModel = viewModel, onDismissFilterDialog = {
                            viewModel.isFilterSlider = !viewModel.isFilterSlider
                        }, onApplyFilter = { filter ->
                            viewModel.orderListWithApplyFilter(filter)
                            viewModel.isFilterSlider = !viewModel.isFilterSlider
                        })
                    }
                }
            }

            if (viewModel.isPickingDialog) {
                PickingDialog(viewModel) {
                    viewModel.isPickingDialog = !viewModel.isPickingDialog
                }
            }
            if (viewModel.isPickingConformDialog) {
                PickingConformDialog(viewModel) {
                    viewModel.isPickingConformDialog = !viewModel.isPickingConformDialog
                }
            }

            if (viewModel.isSignOutDialog) {

                SignOutConfirmationDialog(onConfirmSignOutListener = {
                    applicationContext?.put("login_token", "")
                    applicationContext?.put("picker_id", "")
                    viewModel.onNavigateToSingInScreen()
                }, onCloseSignOutDialog = {
                    viewModel.isSignOutDialog = false
                })
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
        Text("No More Record Found")
    }
}







