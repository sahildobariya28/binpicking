package com.scanner.binpicking.presentation.screen.orderitem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.scanner.binpicking.presentation.screen.orderitem.OrderItemsViewModel
import com.scanner.binpicking.theme.Colors.LightDividerSecondary
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightPrimary
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Composable
fun ItemView(viewModel: OrderItemsViewModel, pos: Int, pickerName: String) {
    Column(
        Modifier.fillMaxWidth()
            .shadow(6.dp, shape = RoundedCornerShape(5.dp), spotColor = LightOnHint)
            .clip(
                RoundedCornerShape(5.dp)
            )
            .border(BorderStroke(0.5.dp, LightPrimary), RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(start = 12.dp, end = 12.dp, top = 15.dp, bottom = 10.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            KamelImage(
                modifier = Modifier.size(50.dp)
                    .border(BorderStroke(0.5.dp, LightPrimary), RoundedCornerShape(5.dp))
                    .padding(8.dp),
                resource = asyncPainterResource(data = viewModel.orderItemList[pos].image) {
                    coroutineContext = Job() + Dispatchers.Main
                },
                contentScale = ContentScale.Fit,
                contentDescription = "Profile",
            )
            Spacer(Modifier.fillMaxHeight().width(10.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = viewModel.orderItemList[pos].productName,
                color = LightOnPrimary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis
            )


            val backgroundColor =
                if (viewModel.orderItemList[pos].pickupStatus == "0") {
                    Color(0xffFFF2DD)
                } else {
                    Color(0xffEFFAF4)
                }
            val textColor = if (viewModel.orderItemList[pos].pickupStatus == "0") {
                Color(0xffE75C11)
            } else {
                Color(0xff3DC16E)
            }

            val text = if (viewModel.orderItemList[pos].pickupStatus == "0") {
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
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Requested Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].qty)
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
                    .background(LightOnHint)
            )
            val collectQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Collect Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].gatherQty)
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
                    .background(LightOnHint)
            )
            val missQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Missing Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].missing)
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
                    .background(LightOnHint)
            )
            val creditQty = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Credit Qty: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].credit)
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
                .background(LightDividerSecondary)
        )

        Row(
            Modifier.padding(vertical = 5.dp).fillMaxWidth().height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val pickName = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Picker Name: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
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
                    .background(LightOnHint)
            )
            val location = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Location: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].location)
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
                    .background(LightOnHint)
            )


            val sku = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightOnPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("SKU: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = LightOnHint
                    )
                ) {
                    append(viewModel.orderItemList[pos].sku)
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