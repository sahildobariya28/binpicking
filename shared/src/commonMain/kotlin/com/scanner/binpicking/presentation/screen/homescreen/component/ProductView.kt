package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.domain.mode.Order
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.Interval
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.resoures.icons.SelectedDone

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun ProductView(
    viewModel: HomeScreenViewModel,
    order: Order,
    crossinline routeItemListScreen: (entityId: String, pickerName: String) -> Unit
) {

    Box {
        Column(
            Modifier.padding(top = 12.dp).fillMaxWidth()
                .shadow(6.dp, shape = RoundedCornerShape(5.dp), spotColor = Colors.theme).clip(
                    RoundedCornerShape(5.dp)
                ).border(
                    width = if (viewModel.selectedOrderList.contains(order)) 2.dp else 0.5.dp,
                    color = if (viewModel.selectedOrderList.contains(order)) Colors.green else Colors.theme,
                    shape = RoundedCornerShape(5.dp)
                ).background(if (viewModel.selectedOrderList.contains(order)) Colors.green_light else Color.White).combinedClickable(
                    onClick = {
                        if (viewModel.isSelectionMode) {
                            if (viewModel.selectedOrderList.contains(order)) {
                                viewModel.selectedOrderList.remove(order)
                                if (viewModel.selectedOrderList.size == 0) {
                                    viewModel.isSelectionMode = false
                                }
                            } else {
                                viewModel.selectedOrderList.add(order)
                            }
                        }
                    }, onLongClick = {
                        if (viewModel.isSelectionMode) {
                            if (viewModel.selectedOrderList.contains(order)) {
                                viewModel.selectedOrderList.remove(order)
                                if (viewModel.selectedOrderList.size == 0) {
                                    viewModel.isSelectionMode = false
                                }
                            } else {
                                viewModel.selectedOrderList.add(order)
                            }

                        } else {
                            viewModel.isSelectionMode = true
                            viewModel.selectedOrderList.add(order)
                        }
                    }).padding(start = 12.dp, end = 12.dp, top = 15.dp, bottom = 10.dp)
        ) {

            Row(Modifier.height(IntrinsicSize.Min).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier.clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFFCDD3D8))
                        .border(
                            width = 0.5.dp,
                            color = Colors.theme,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = order.orders[0],
                        color = Colors.text_black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                }

                Divider(
                    color = Colors.theme,
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .fillMaxHeight()
                        .width(1.dp)
                )

                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = order.picker,
                        color = Colors.text_black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        fontSize = 24.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Box {

                    val backgroundColor = when (order.status) {
                        "0" -> Color(0xffFFF2DD)
                        "1" -> Color(0xffEFFAF4)
                        "2" -> Color(0xffE5F8FE)
                        else -> Color(0xffFFF2DD)
                    }
                    val textColor = when (order.status) {
                        "0" -> Color(0xffE75C11)
                        "1" -> Color(0xff3DC16E)
                        "2" -> Color(0xff1A86D0)
                        else -> Color(0xffE75C11)
                    }
                    val text = when (order.status) {
                        "0" -> "Pending"
                        "1" -> "Completed"
                        "2" -> "Processing"
                        else -> "Null"
                    }

                    Box(
                        Modifier.clip(RoundedCornerShape(5.dp))
                            .border(BorderStroke(1.dp, textColor), RoundedCornerShape(5.dp))
                            .background(backgroundColor)
                            .padding(horizontal = 15.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(Modifier.height(IntrinsicSize.Min).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                val brandText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Colors.text_theme,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Picker Name: ")
                    }
                    withStyle(style = SpanStyle(
                        color = Colors.text_theme_light
                    )) {
                        append("${order.picker}")
                    }
                }
                Text(
                    text = brandText,
                    color = Colors.text_hint,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Box(Modifier.padding(vertical = 20.dp).fillMaxWidth().height(1.dp).background(Colors.divider))


            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(18.dp),
                            imageVector = MyIconPack.Interval,
                            contentDescription = null
                        )

                        Spacer(Modifier.width(5.dp))

                        Text(
                            text = if (order.interval.toString().equals("null")) "... (Minutes)" else "${order.interval.toString()} (Minutes)",
                            color = Colors.text_hint,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }

                    val text = when (order.actionStatus) {
                        0 -> "Continue Picking"
                        1 -> "Item List"
                        else -> null
                    }

                    if (order.actionStatus == 0 || order.actionStatus == 1) {
                        Box(
                            Modifier.clip(
                                RoundedCornerShape(5.dp)
                            ).background(Colors.theme).clickable {
                                if (text.equals("Item List")) {
                                    routeItemListScreen(order.entityId, order.picker)
                                } else {
                                    viewModel.continuePicking(order.entityId)
                                }
                            }.padding(horizontal = 15.dp, vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (text != null) {
                                Text(
                                    text = text,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }

                Text(
                    text = order.startingTime.toString(),
                    color = Colors.text_hint,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

        }

        if (viewModel.selectedOrderList.contains(order)) {
            Image(
                modifier = Modifier.size(24.dp).offset(x = 15.dp),
                imageVector = MyIconPack.SelectedDone,
                contentDescription = null
            )
        }
    }
}
