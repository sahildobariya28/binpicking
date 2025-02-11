package com.scanner.binpicking.presentation.screen.orderitem

import ContentWithMessageBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.screen.orderitem.component.AppBar
import com.scanner.binpicking.presentation.screen.orderitem.component.ItemView
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightBackground
import com.scanner.binpicking.theme.Colors.LightError
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.NeutralBlack
import com.scanner.binpicking.theme.Colors.NeutralWhite
import kotlinx.datetime.Clock
import rememberMessageBarState


@Composable
fun ItemListScreen(viewModel: OrderItemsViewModel) {
    viewModel.messageBarState = rememberMessageBarState()
    LaunchedEffect(Unit) {
        viewModel.getOrderItemList(viewModel.entityId)
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(onCloseDialog = {
                viewModel.onBackPressed()
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

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    item {
                        if (viewModel.orderItemList.isNotEmpty()) {
                            Row(
                                Modifier.padding(top = 15.dp, bottom = 25.dp).fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .background(NeutralWhite)
                                    .padding(vertical = 15.dp, horizontal = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(3.dp))
                                        .background(Color(0xFFCDD3D8))
                                        .border(
                                            width = 0.5.dp,
                                            color = LightPrimary,
                                            shape = RoundedCornerShape(5.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 3.dp)
                                ) {

                                    Text(
                                        text = viewModel.orderItemList[0].orderNumber,
                                        color = NeutralBlack,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 22.sp
                                    )
                                }

                                Divider(
                                    color = LightPrimary,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                        .fillMaxHeight()
                                        .width(1.dp)
                                )

                                Box(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = viewModel.pickerName,
                                        color = NeutralBlack,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 1,
                                        fontSize = 24.sp,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                    items(count = viewModel.orderItemList.size, key = {
                        "${viewModel.orderItemList[it].entityId}_${Clock.System.now().toEpochMilliseconds()}"
                    }) { pos ->
                        ItemView(viewModel, pos, viewModel.pickerName)
                    }
                }
                if (viewModel.isLoading) {
                    ShowLoadingDialog()
                }
            }
        }
    }
}





