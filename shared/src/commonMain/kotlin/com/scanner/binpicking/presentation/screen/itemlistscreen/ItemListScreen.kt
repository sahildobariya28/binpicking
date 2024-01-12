package com.scanner.binpicking.presentation.screen.itemlistscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.domain.mode.ItemListModelList
import com.scanner.binpicking.presentation.component.ShowLoadingDialog
import com.scanner.binpicking.presentation.screen.single_picking.AppBar
import com.scanner.binpicking.resoures.Colors
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job


@Composable
fun ItemListScreen(entityId: String, pickerName: String, onSuccessFinishOrder: () -> Unit) {
    val viewModel = getViewModel(key = "item-list-model", factory = viewModelFactory {
        ItemListViewModel(entityId)
    })

    Box(contentAlignment = Alignment.Center) {

        Column(modifier = Modifier.fillMaxSize().background(Colors.background)) {

            AppBar(onCloseDialog = {
                onSuccessFinishOrder()
            })

            val orderListState by viewModel.orderList.collectAsState()
            LaunchedEffect(orderListState) {
                if (orderListState.isLoading) {
                    viewModel.isLoading = true
                } else if (orderListState.status.isSuccess()) {
                    (orderListState.data as ItemListModelList).let { newData ->
                        viewModel.isLoading = false
                        viewModel.orderItemList.clear()
                        viewModel.orderItemList.addAll(newData.data)

                    }

                } else if (!orderListState.status.isSuccess()) {
                    viewModel.isLoading = false
                }
            }



            if (viewModel.orderItemList.isNotEmpty()) {
                Row(
                    Modifier.padding(top = 15.dp, bottom = 25.dp).fillMaxWidth().height(IntrinsicSize.Min)
                        .background(Colors.white).padding(vertical = 15.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

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
                            text = viewModel.orderItemList[0].orderNumber,
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
                            text = pickerName,
                            color = Colors.text_black,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            fontSize = 24.sp,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp)
            ) {
                items(viewModel.orderItemList.size) { pos ->
                    ItemView(viewModel, pos, pickerName)
                }
            }

            if (viewModel.isLoading) {
                ShowLoadingDialog()
            }
        }
    }
}

@Composable
fun ItemView(viewModel: ItemListViewModel, pos: Int, pickerName: String) {
    Column(
        Modifier.fillMaxWidth()
            .shadow(6.dp, shape = RoundedCornerShape(5.dp), spotColor = Colors.text_hint)
            .clip(
                RoundedCornerShape(5.dp)
            )
            .border(BorderStroke(0.5.dp, Colors.theme), RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(start = 12.dp, end = 12.dp, top = 15.dp, bottom = 10.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            KamelImage(
                modifier = Modifier.size(50.dp)
                    .border(BorderStroke(0.5.dp, Colors.theme), RoundedCornerShape(5.dp))
                    .padding(8.dp),
                resource = asyncPainterResource(data = viewModel.orderItemList[pos].image) {
                    coroutineContext = Job() + Dispatchers.IO
                },
                contentScale = ContentScale.Fit,
                contentDescription = "Profile",
            )
            Spacer(Modifier.fillMaxHeight().width(10.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = viewModel.orderItemList[pos].productName,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis
            )


            val backgroundColor =
                if (viewModel.orderItemList[pos].pickupStatus.equals("0")) {
                    Color(0xffFFF2DD)
                } else {
                    Color(0xffEFFAF4)
                }
            val textColor = if (viewModel.orderItemList[pos].pickupStatus.equals("0")) {
                Color(0xffE75C11)
            } else {
                Color(0xff3DC16E)
            }

            val text = if (viewModel.orderItemList[pos].pickupStatus.equals("0")) {
                "Pending"
            } else {
                "Picked"
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
                    fontSize = 16.sp
                )
            }
        }

        Spacer(Modifier.fillMaxWidth().height(10.dp))

        Row(
            Modifier.padding(vertical = 5.dp).fillMaxWidth().height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val reqQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Requested Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].qty}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = reqQty,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Spacer(
                Modifier.padding(horizontal = 30.dp).fillMaxHeight(.9f).width(1.dp)
                    .background(Colors.text_hint)
            )
            val collectQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Collect Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].gatherQty}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = collectQty,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Spacer(
                Modifier.padding(horizontal = 30.dp).fillMaxHeight(.9f).width(1.dp)
                    .background(Colors.text_hint)
            )
            val missQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Missing Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].missing}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = missQty,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Spacer(
                Modifier.padding(horizontal = 30.dp).fillMaxHeight(.9f).width(1.dp)
                    .background(Colors.text_hint)
            )
            val creditQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Credit Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].credit}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = creditQty,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )


        }

        Spacer(
            Modifier.padding(vertical = 10.dp).fillMaxWidth().height(1.dp)
                .background(Colors.divider)
        )

        Row(
            Modifier.padding(vertical = 5.dp).fillMaxWidth().height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val pickName = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Picker Name: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append(viewModel.orderItemList[pos].picker)
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp).weight(1f),
                text = pickName,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Spacer(
                Modifier.padding(horizontal = 15.dp).fillMaxHeight(.9f).width(1.dp)
                    .background(Colors.text_hint)
            )
            val location = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Location: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].location}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp).weight(1f),
                text = location,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                Modifier.padding(horizontal = 15.dp).fillMaxHeight(.9f).width(1.dp)
                    .background(Colors.text_hint)
            )


            val sku = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("SKU: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Colors.text_theme_light
                    )
                ) {
                    append("${viewModel.orderItemList[pos].sku}")
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp).weight(1f),
                text = sku,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        }

    }
}

