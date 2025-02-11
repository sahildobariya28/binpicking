package com.scanner.binpicking.presentation.screen.picking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.presentation.utils.pxToDp
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.LightSuccessPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.jetbrains.compose.resources.painterResource
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.btn_keyboard
import binpicking.shared.generated.resources.btn_minus
import binpicking.shared.generated.resources.btn_plus
import binpicking.shared.generated.resources.btn_previous
import binpicking.shared.generated.resources.btn_skip
import binpicking.shared.generated.resources.img_scanner
import com.scanner.binpicking.core.AppConfig

@Composable
fun NewPickingTopComponent(pickingList: PickingModelWrapper) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth().height(45.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFFEEEEEE))
                    .wrapContentHeight(Alignment.CenterVertically),
                text = pickingList.data.orderNumber,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = LightOnPrimary,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Text(
                modifier = Modifier.fillMaxWidth().height(45.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFFEEEEEE))
                    .wrapContentHeight(Alignment.CenterVertically),
                text = pickingList.data.shipping,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = LightOnPrimary,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }

        // Divider
        Box(Modifier.height(100.dp).width(1.dp).background(Color(0xFF6d39a8)))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.End
        ) {
            val brandText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xff07a400), fontWeight = FontWeight.Bold)) {
                    append("${pickingList.data.ordergetqty} ")
                }
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append("/ ${pickingList.data.ordertotalqty}")
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth().height(45.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFFEEEEEE))
                    .wrapContentHeight(Alignment.CenterVertically),
                text = brandText,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Text(
                modifier = Modifier.fillMaxWidth().height(45.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xffea2a25))
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "NO",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }
    }

    Spacer(Modifier.fillMaxWidth().height(20.dp))

    Text(
        modifier = Modifier.fillMaxWidth().height(65.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xffea2a25))
            .wrapContentHeight(Alignment.CenterVertically),
        text = "BIN NO: ${pickingList.data.location}",
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
    )

    Spacer(Modifier.fillMaxWidth().height(10.dp))
}

@Composable
fun NewPickingMiddleComponent(
    viewModel: PickingViewModel,
    pickingModel: PickingModelWrapper,
    onMissingChanged: () -> Unit,
    onInvalidQtyError: () -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(25.dp), verticalAlignment = Alignment.CenterVertically) {

        var imageHeight by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier.fillMaxWidth(.5f).onSizeChanged { imageHeight = it.height }
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(5.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            KamelImage(
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp).fillMaxWidth().aspectRatio(1.1f),
                resource = asyncPainterResource(data = pickingModel.data.imageUrl) {
                    coroutineContext = Job() + Dispatchers.Main
                },
                contentScale = ContentScale.Fit,
                contentDescription = "Profile",
            )

            Text(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 20.dp)
                    .fillMaxWidth().height(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(LightPrimary).wrapContentHeight(Alignment.CenterVertically),
                text = "${(viewModel.uiGatherQty + pickingModel.data.gatherQty) + (pickingModel.data.missing)} / ${pickingModel.data.qty}",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }

        Column(
            Modifier.fillMaxWidth().height(pxToDp(imageHeight.toFloat())),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                Modifier.weight(1f)
                    .clip(RoundedCornerShape(5.dp))
                    .border(BorderStroke(1.dp, Color(0xFFECECEC)), RoundedCornerShape(5.dp))
                    .background(NeutralWhite)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    Modifier.weight(1f).clip(RoundedCornerShape(5.dp)).border(1.dp, LightPrimary, RoundedCornerShape(5.dp)).background(Color.White)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        modifier = Modifier.size(40.dp).clickable {
                            if (viewModel.uiMissingQty > 0) {
                                viewModel.uiMissingQty--
//                                GlobalScope.launch(Dispatchers.Main) {
//                                    LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "missing count decrement ${viewModel.uiMissingQty}")
//                                }
                            }
                        },
                        painter = painterResource(Res.drawable.btn_minus),
                        contentDescription = "Minus Button"
                    )
                    BasicTextField(
                        modifier = Modifier.weight(1f).fillMaxHeight().wrapContentHeight(Alignment.CenterVertically),
                        value = "${viewModel.uiMissingQty}",
                        onValueChange = {
                            if (it.isNotBlank()) {
                                if (it.matches("[0-9]+".toRegex())) {
                                    if (it.toInt() <= (pickingModel.data.qty.toInt() - ((pickingModel.data.gatherQty + viewModel.uiGatherQty) + pickingModel.data.missing))) {
                                        viewModel.uiMissingQty = it.toInt()
                                    }
                                }
                            } else {
                                viewModel.uiMissingQty = 0
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Decimal
                        ),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        singleLine = true,
                    )
                    Image(
                        modifier = Modifier.size(40.dp).clickable {
                            if ((viewModel.uiMissingQty + 1) <= (pickingModel.data.qty.toInt() - ((viewModel.uiGatherQty + pickingModel.data.gatherQty) + pickingModel.data.missing))) {
                                viewModel.uiMissingQty++
//                                GlobalScope.launch(Dispatchers.Main) {
//                                    LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "missing count increment ${viewModel.uiMissingQty}")
//                                }
                            }
                        },
                        painter = painterResource(Res.drawable.btn_plus),
                        contentDescription = "Plus Button"
                    )
                }


                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(5.dp)).background(LightSuccessPrimary)
                        .clickable {
                            val mis = viewModel.uiMissingQty
                            val gat = viewModel.uiGatherQty - pickingModel.data.gatherQty

                            if ((mis + gat + pickingModel.data.gatherQty) <= pickingModel.data.qty.toInt()) {
                                onMissingChanged()
                            } else {
                                onInvalidQtyError()
                            }
//                            GlobalScope.launch(Dispatchers.Main) {
//                                LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "missing button click: uimissing : ${mis}, uigatherQTY : ${gat} data gatherQty: ${pickingModel.data.gatherQty}")
//                            }
                        }.wrapContentHeight(Alignment.CenterVertically),
                    text = "MISSING",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
            }
            Column(
                Modifier.weight(1f).padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row {

                    Row(Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(5.dp)).background(Color(0xffea2a25)).padding(5.dp)) {
                        Text(
                            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 5.dp),
                            text = "SKU: ${pickingModel.data.sku}",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                        )
                    }
                }


                Row(Modifier.fillMaxWidth().weight(1f)) {

                    Text(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(5.dp))
                            .background(LightPrimary).padding(vertical = 10.dp, horizontal = 5.dp).wrapContentHeight(Alignment.CenterVertically),
                        text = "STOCK: ${pickingModel.data.totalQty} PCS",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )

                    Spacer(Modifier.width(10.dp))

                    Text(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xffea2a25))
                            .clickable {
                                viewModel.lowQtyDataUpdate()
                            }.padding(vertical = 10.dp, horizontal = 5.dp).wrapContentHeight(Alignment.CenterVertically),
                        text = "Low Qty",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun NewPickingBottomComponent(
    viewModel: PickingViewModel,
    pickingModel: PickingModelWrapper,
    onGatherQtyChanged: () -> Unit,
    onSkuNotMatched: (errorMsg: String) -> Unit
) {

    Row {
        TextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xff07a400))
                .focusRequester(viewModel.focusRequester)
                .wrapContentHeight(Alignment.CenterVertically),
            value = viewModel.productScannerText,
            onValueChange = {
                viewModel.productScannerText = it
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (viewModel.uiGatherQty < pickingModel.data.qty.toInt()) {
                        if (pickingModel.data.sku == viewModel.productScannerText.text) {
                            viewModel.productScannerText = TextFieldValue("")
                            onGatherQtyChanged()
                        } else {
                            viewModel.productScannerText = TextFieldValue("")
                            onSkuNotMatched("SKU Not Matched !...")
                        }
                    }
//                    GlobalScope.launch(Dispatchers.Main) {
//                        LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "keyboard action done click ")
//                    }
                }
            ),
            leadingIcon = {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .height(40.dp)
                            .aspectRatio(1.1f),
                        painter = painterResource(Res.drawable.img_scanner),
                        contentDescription = "Scanner Image"
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = Color.White,
                cursorColor = Color.White
            )
        )
        val isKeyboardOpen by keyboardAsState()
        Box(
            Modifier.padding(start = 20.dp).size(65.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xffeeeeee)).clickable {
                    if (isKeyboardOpen) {
                        viewModel.hideKeyboard()
                    } else {
                        viewModel.showKeyboard()
                    }
                }, contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(45.dp),
                painter = painterResource(Res.drawable.btn_keyboard),
                contentDescription = "Keyboard Button"
            )
        }
    }

    Spacer(Modifier.height(10.dp))

    Row(
        Modifier.fillMaxWidth().height(65.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {

        Row(
            Modifier.weight(1f).height(65.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)).clickable {
                viewModel.previousProduct()
//                GlobalScope.launch(Dispatchers.Main) {
//                    LogErrorRepository.logError("info:  ${AppConfig.getPickerId()}  :::" + "previous button click")
//                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier.size(35.dp),
                painter = painterResource(Res.drawable.btn_previous),
                contentDescription = "Previous Button"
            )
            Spacer(Modifier.width(10.dp).fillMaxHeight())
            Text(
                modifier = Modifier,
                text = "Previous",
                fontWeight = FontWeight.Bold,
                color = LightOnPrimary,
                fontSize = 20.sp,
            )
        }

//        Row(
//            Modifier.weight(1f).height(65.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xff07a400)).clickable {
//                if (pickingModel.data.location.startsWith("1").or(pickingModel.data.location.startsWith("2"))) {
//                    viewModel.messageBarState.addError(Exception("Please Scan Product Manually"))
//                } else {
//                    if ((pickingModel.data.gatherQty + viewModel.uiGatherQty) < pickingModel.data.qty.toInt()) {
//                        viewModel.productScannerText = TextFieldValue("")
//                        onGatherQtyChanged()
//                    } else {
//                        viewModel.productScannerText = TextFieldValue("")
//                        onSkuNotMatched("SKU Not Matched !...")
//                    }
//                }
////                GlobalScope.launch(Dispatchers.Main) {
////                    LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "pick button click")
////                }
//            },
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Image(
//                modifier = Modifier.size(40.dp),
//                painter = painterResource(Res.drawable.img_scanner),
//                contentDescription = "Scanner Button",
//                colorFilter = ColorFilter.tint(
//                    Color.White
//                )
//            )
//            Spacer(Modifier.width(5.dp))
//            Text(
//                text = "PICK",
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//                fontSize = 20.sp,
//            )
//        }

        Row(
            Modifier.weight(1f).height(65.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)).clickable {
                AppConfig.getPickerId()?.let { pickerId ->
                    viewModel.skipProduct(pickerId.toInt())
//                    GlobalScope.launch(Dispatchers.Main) {
//                        LogErrorRepository.logError("info  ${AppConfig.getPickerId()}  :::" + "skip button click")
//                    }
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Skip",
                fontWeight = FontWeight.Bold,
                color = LightOnPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.width(10.dp).fillMaxHeight())
            Image(
                modifier = Modifier.size(35.dp),
                painter = painterResource(Res.drawable.btn_skip),
                contentDescription = "Skip Button"
            )
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}

