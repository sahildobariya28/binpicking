package com.scanner.binpicking.presentation.screen.single_picking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.isIos
import com.scanner.binpicking.presentation.util.pxToDp
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.BtnMinus
import com.scanner.binpicking.resoures.icons.BtnPlus
import com.scanner.binpicking.resoures.icons.BtnPrevious
import com.scanner.binpicking.resoures.icons.BtnSkip
import com.scanner.binpicking.resoures.icons.Keyboard
import com.scanner.binpicking.resoures.icons.Logobiglight
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.resoures.icons.Scanner
import com.scanner.binpicking.topSafeArea
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job

@Composable
fun AppBar(onCloseDialog: () -> Unit) {


    Row(
        Modifier.fillMaxWidth().height(pxToDp(topSafeArea) + 70.dp).background(Colors.theme)
            .padding(top = topSafeArea.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            modifier = Modifier.size(70.dp)
                .clickable {
                    onCloseDialog()
                }.padding(15.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )

        Box(
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                imageVector = MyIconPack.Logobiglight,
                contentDescription = "logo",
            )
        }

        // Search icon button
        Icon(
            modifier = Modifier.size(70.dp)
                .clickable {

                },
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.White
        )

    }
}

@Composable
fun SinglePickingTopComponent(singlePickingList: SinglePickingModel) {
    Row(
        modifier = Modifier.padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

            Box(
                Modifier.width(250.dp).height(45.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                    text = "${singlePickingList.orderNumber}",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Colors.text_theme,
                    textAlign = TextAlign.End,
                    fontSize = 20.sp
                )

            }

            Box(
                Modifier.width(250.dp).height(45.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                    text = "${singlePickingList.shipping}",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Colors.text_theme,
                    textAlign = TextAlign.End,
                    fontSize = 20.sp
                )
            }

        }

        Box(Modifier.height(100.dp).width(50.dp).background(Color(0xFF6d39a8)))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.End
        ) {
            var qtyBoxWidth by remember { mutableStateOf(0) }

            Box(
                Modifier.wrapContentWidth().defaultMinSize(minWidth = 200.dp).height(45.dp)
                    .onSizeChanged { qtyBoxWidth = it.width }
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFEEEEEE))
                    .padding(horizontal = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                val brandText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xff07a400),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("${singlePickingList.ordergetqty} ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("/ ${singlePickingList.ordertotalqty}")
                    }
                }

                Text(
                    modifier = Modifier.wrapContentWidth().padding(horizontal = 5.dp),
                    text = brandText,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Box(
                Modifier.width(pxToDp(qtyBoxWidth.toFloat())).height(45.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xffea2a25)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = "NO",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

    Spacer(Modifier.fillMaxWidth().height(20.dp))
    Row(
        Modifier.fillMaxWidth().height(65.dp).clip(RoundedCornerShape(5.dp))
            .background(Color(0xffea2a25)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val brandText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("BIN NO: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("${singlePickingList.location}")
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = brandText,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
    Spacer(Modifier.fillMaxWidth().height(10.dp))
}

@Composable
fun SinglePickingMiddleComponent(
    viewModel: SinglePickingViewModel,
    singlePickingModel: SinglePickingModel,
    onMissingChanged: () -> Unit,
    onInvalidQtyError: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var imageHeight by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier.fillMaxWidth(.5f).onSizeChanged { imageHeight = it.height }
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(5.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            KamelImage(
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp).fillMaxWidth()
                    .aspectRatio(1.1f),
                resource = asyncPainterResource(data = singlePickingModel.imageUrl) {
                    coroutineContext = Job() + Dispatchers.IO
                },
                contentScale = ContentScale.Fit,
                contentDescription = "Profile",
            )

            Row(
                Modifier.padding(start = 25.dp, end = 25.dp, bottom = 20.dp)
                    .fillMaxWidth().height(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Colors.theme),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "${singlePickingModel.missing.toInt() + viewModel.gatherQty} / ${singlePickingModel.qty}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            Modifier.fillMaxWidth().height(pxToDp(imageHeight.toFloat())).padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                Modifier.weight(1f)
                    .clip(RoundedCornerShape(5.dp))
                    .border(BorderStroke(1.dp, Color(0xFFECECEC)), RoundedCornerShape(5.dp))
                    .background(Colors.white)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {


                Row(
                    Modifier.weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .border(
                            1.dp,
                            Colors.theme,
                            RoundedCornerShape(5.dp)
                        ).background(Color.White).padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(40.dp).clickable {
                                if (viewModel.missingQty > 0) {
                                    viewModel.missingQty--
                                }
                            },
                            imageVector = MyIconPack.BtnMinus,
                            contentDescription = null
                        )

                        Box(
                            Modifier.weight(1f).fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                Modifier.fillMaxWidth().fillMaxHeight(.7f),
                                contentAlignment = Alignment.Center
                            ) {
                                BasicTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = "${viewModel.missingQty}",
                                    onValueChange = {
                                        if (it.isNotBlank()) {
                                            if (it.matches("[0-9]+".toRegex())) {
                                                if (it.toInt() <= singlePickingModel.qty.toInt()) {
                                                    viewModel.missingQty = it.toInt()
                                                }
                                            }

                                        } else {
                                            viewModel.missingQty = 0
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Decimal // Allow any text input
                                    ),
                                    textStyle = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    singleLine = true,
                                    maxLines = 1
                                )
                            }
                        }

                        Image(
                            modifier = Modifier.size(40.dp).clickable {
                                if (viewModel.missingQty < (singlePickingModel.qty.toInt() - (singlePickingModel.gatherQty.toInt() + viewModel.gatherQty))) {
                                    viewModel.missingQty++
                                }
                            },
                            imageVector = MyIconPack.BtnPlus,
                            contentDescription = null
                        )
                    }
                }

                Box(
                    Modifier.fillMaxWidth().weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Colors.green)
                        .clickable {
                            if (viewModel.missingQty + (singlePickingModel.gatherQty.toInt() + viewModel.gatherQty) <= singlePickingModel.qty.toInt()) {
                                onMissingChanged()
                            } else {
                                onInvalidQtyError()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = "MISSING",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Column(
                Modifier.weight(1f).padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    Modifier.weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xffea2a25)),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val brandText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("SKU:")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("${singlePickingModel.sku}")
                        }
                    }

                    SelectionContainer(Modifier.fillMaxWidth().weight(1f)) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                            text = brandText,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }

                }

                Box(
                    Modifier.weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Colors.theme),
                    contentAlignment = Alignment.Center
                ) {

                    val brandText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("STOCK:")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("${singlePickingModel.totalQty} PCS")
                        }
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                        text = brandText,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SinglePickingBottomComponent(
    viewModel: SinglePickingViewModel,
    singlePickingModel: SinglePickingModel,
    onGatherQtyChanged: () -> Unit,
    onSkuNotMatched: (errorMsg: String) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        if (isIos) {
            keyboardController?.hide()
        } else {
            keyboardController?.hide()
        }
    }


    Row {
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth().height(65.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xff07a400)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxSize().focusRequester(focusRequester),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (viewModel.gatherQty < singlePickingModel.qty.toInt()) {
                            if (singlePickingModel.sku.equals(textFieldValue.text)) {
//                                textFieldValue = textFieldValue.copy(
//                                    selection = TextRange(
//                                        0,
//                                        textFieldValue.text.length
//                                    )
//                                )
                                textFieldValue = TextFieldValue("")
                                onGatherQtyChanged()
                            } else {
                                textFieldValue = textFieldValue.copy(
                                    selection = TextRange(
                                        0,
                                        textFieldValue.text.length
                                    )
                                )
                                onSkuNotMatched("SKU Not Matched !...")
                            }
                        }
                        //this place to unfocus keyboard

                    }
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                singleLine = true,
                maxLines = 1,
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                TextFieldDefaults.TextFieldDecorationBox(
                    value = textFieldValue.text,
                    innerTextField = it,
                    singleLine = true,
                    enabled = true,
                    visualTransformation = VisualTransformation.None,
                    leadingIcon = {
                        Image(
                            modifier = Modifier.height(40.dp).aspectRatio(1.1f),
                            imageVector = MyIconPack.Scanner,
                            contentDescription = ""
                        )
                    },
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(0.dp)
                )
            }
        }
        val isKeyboardOpen by keyboardAsState()
        Box(
            Modifier.padding(start = 20.dp).size(65.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xffeeeeee)).clickable {
                    if (isKeyboardOpen) {
                        focusRequester.requestFocus()
                        keyboardController?.hide()
                    } else {
                        focusRequester.requestFocus()
                        keyboardController?.show()
                    }


                }, contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(45.dp),
                imageVector = MyIconPack.Keyboard,
                contentDescription = null
            )
        }
    }


    Spacer(Modifier.height(10.dp))

    Row(
        Modifier.fillMaxWidth().height(65.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row(
            Modifier.weight(1f).height(65.dp)
                .clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)).clickable {
                    viewModel.previousProduct(
                        singlePickingModel.entityId.toInt(),
                        singlePickingModel.mainRefEntityId.toInt()
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier.size(35.dp),
                imageVector = MyIconPack.BtnPrevious,
                contentDescription = null
            )
            Spacer(Modifier.width(10.dp).fillMaxHeight())
            Text(
                modifier = Modifier,
                text = "Previous",
                fontWeight = FontWeight.Bold,
                color = Colors.text_theme,
                fontSize = 20.sp,
            )

        }

        Row(
            Modifier.weight(1f).height(65.dp)
                .clip(RoundedCornerShape(5.dp)).background(Color(0xff07a400)).clickable {
                    if (viewModel.gatherQty < singlePickingModel.qty.toInt()) {
                        onGatherQtyChanged()
                    } else {

                        textFieldValue = textFieldValue.copy(
                            selection = TextRange(
                                0,
                                textFieldValue.text.length
                            )
                        )
                        onSkuNotMatched("SKU Not Matched !...")
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                imageVector = MyIconPack.Scanner,
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Color.White
                )
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = "PICK",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
            )
        }

        Row(
            Modifier.weight(1f).height(65.dp)
                .clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)).clickable {
                    applicationContext?.getString("picker_id")?.let { pickerId ->
                        viewModel.skipProduct(
                            viewModel.gatherQty,
                            singlePickingModel.gatherQty.toInt(),
                            singlePickingModel.sku,
                            pickerId.toInt(),
                            singlePickingModel.entityId.toInt(),
                            singlePickingModel.mainRefEntityId.toInt()
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Skip",
                fontWeight = FontWeight.Bold,
                color = Colors.text_theme,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.width(10.dp).fillMaxHeight())
            Image(
                modifier = Modifier.size(35.dp),
                imageVector = MyIconPack.BtnSkip,
                contentDescription = null
            )
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}

