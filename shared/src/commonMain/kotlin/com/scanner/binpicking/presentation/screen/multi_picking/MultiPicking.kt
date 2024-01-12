package com.scanner.binpicking.presentation.screen.multi_picking

import androidx.compose.runtime.Composable

@Composable
fun MultiPicking(onCloseDialog: () -> Unit) {

//    var viewModel: SinglePickingViewModel =
//        viewModelFactory { SinglePickingViewModel() }.createViewModel()
//
//    Box(
//        Modifier.fillMaxSize().background(Color.Black.copy(alpha = .5f)).clickable { onCloseDialog() },
//        contentAlignment = Alignment.Center
//    ) {
//
//
//        Box(
//            Modifier.fillMaxHeight(.75f).aspectRatio(85f / 100f).clickable(enabled = false) {  }.clip(RoundedCornerShape(5.dp))
//                .background(Color.White).padding(10.dp), contentAlignment = Alignment.Center
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(15.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Row(
//                    Modifier.fillMaxWidth().weight(12f),
//                    horizontalArrangement = Arrangement.spacedBy(5.dp)
//                ) {
//                    Row(
//                        Modifier.fillMaxHeight().weight(3f),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Column(Modifier.fillMaxHeight().weight(1f)) {
//                            var parentSize by remember {
//                                mutableStateOf(IntSize.Zero)
//                            }
//                            Box(
//                                Modifier.fillMaxWidth().onSizeChanged { parentSize = it }
//                                    .clip(RoundedCornerShape(5.dp)).weight(1f)
//                                    .background(Color(0xFFEEEEEE)),
//                                contentAlignment = Alignment.Center
//                            ) {
//
//                                SmartText(
//                                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                    text = "#313543",
//                                    fontWeight = FontWeight.ExtraBold,
//                                    color = Color.Black,
//                                    textAlign = TextAlign.End
//                                )
//
//                            }
//                            Spacer(Modifier.height(5.dp))
//                            Row(
//                                Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp)).weight(1f)
//                                    .background(Color(0xFFEEEEEE)),
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//
//                                Image(
//                                    modifier = Modifier.size(20.dp),
//                                    imageVector = MyIconPack.Logosmall,
//                                    contentDescription = null
//                                )
//
//                                Spacer(Modifier.width(5.dp))
//
//                                SmartText(
//                                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                    text = "UPS Overnight",
//                                    fontWeight = FontWeight.SemiBold,
//                                    color = Color.Black,
//                                    textAlign = TextAlign.End,
//                                    downScale = 50f,
//                                    isAutoSetWidth = true
//                                )
//                            }
//
//                        }
//                        Spacer(Modifier.width(5.dp))
//                        Box(Modifier.fillMaxHeight().width(30.dp).background(Color(0xFF6d39a8)))
//                    }
//
//                    Box(
//                        Modifier.fillMaxHeight().weight(1f), contentAlignment = Alignment.Center
//                    ) {
//
//                    }
//
//                    Box(
//                        Modifier.fillMaxHeight().weight(3f), contentAlignment = Alignment.Center
//                    ) {
//                        Box(
//                            Modifier.fillMaxHeight(.8f).clip(RoundedCornerShape(5.dp))
//                                .background(Color(0xFFEEEEEE))
//                                .padding(horizontal = 25.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            val brandText = buildAnnotatedString {
//                                withStyle(
//                                    style = SpanStyle(
//                                        color = Color(0xff07a400),
//                                        fontWeight = FontWeight.SemiBold
//                                    )
//                                ) {
//                                    append("3 ")
//                                }
//                                withStyle(
//                                    style = SpanStyle(
//                                        color = Color.Black,
//                                        fontWeight = FontWeight.ExtraBold
//                                    )
//                                ) {
//                                    append("/ 15")
//                                }
//                            }
//                            SmartText(
//                                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                text = brandText,
//                                fontWeight = FontWeight.SemiBold,
//                                color = Color.White,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                }
//
//                Row(
//                    Modifier.fillMaxWidth().weight(10f).clip(RoundedCornerShape(5.dp))
//                        .background(Color(0xffea2a25)),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    val brandText = buildAnnotatedString {
//                        withStyle(
//                            style = SpanStyle(
//                                fontWeight = FontWeight.Medium
//                            )
//                        ) {
//                            append("BIN NO: ")
//                        }
//                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
//                            append("2-C7-4B")
//                        }
//                    }
//                    SmartText(
//                        modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                        text = brandText,
//                        fontWeight = FontWeight.ExtraBold,
//                        color = Color.White,
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//                Row(
//                    Modifier.fillMaxWidth().weight(61f).clip(RoundedCornerShape(5.dp)),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.weight(1f),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Column(
//                            Modifier.weight(7f).fillMaxWidth(.8f)
//                                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(5.dp))
//                                .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Image(
//                                modifier = Modifier.weight(8f).aspectRatio(1.1f).padding(15.dp),
//                                imageVector = MyIconPack.Logosmall,
//                                contentDescription = null,
//                            )
//                            Spacer(Modifier.height(5.dp))
//                            Row(
//                                Modifier.weight(2f).fillMaxWidth(.8f)
//                                    .clip(RoundedCornerShape(5.dp))
//                                    .background(Color(0xff333333)),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//
//                                SmartText(
//                                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                    text = "0 / 1",
//                                    fontWeight = FontWeight.ExtraBold,
//                                    color = Color.White,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//
//                        }
//
//                        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                            Row(
//                                Modifier.fillMaxHeight().clip(RoundedCornerShape(5.dp))
//                                    .background(Color(0xffea2a25)).padding(horizontal = 10.dp),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                val brandText = buildAnnotatedString {
//                                    withStyle(
//                                        style = SpanStyle(
//                                            fontWeight = FontWeight.Medium
//                                        )
//                                    ) {
//                                        append("SKU:")
//                                    }
//                                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
//                                        append("SGS22U-SPBLK")
//                                    }
//                                }
//
//                                SmartText(
//                                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                    text = brandText,
//                                    fontWeight = FontWeight.SemiBold,
//                                    color = Color.White,
//                                    textAlign = TextAlign.Center,
//                                    isAutoSetWidth = true
//                                )
//                            }
//                        }
//
//                        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                            Row(
//                                Modifier.fillMaxHeight(.8f).clip(RoundedCornerShape(5.dp))
//                                    .background(Color(0xff333333)).padding(horizontal = 10.dp),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                val brandText = buildAnnotatedString {
//                                    withStyle(
//                                        style = SpanStyle(
//                                            fontWeight = FontWeight.Medium
//                                        )
//                                    ) {
//                                        append("STOCK:")
//                                    }
//                                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
//                                        append("41 PCS")
//                                    }
//                                }
//                                SmartText(
//                                    modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
//                                    text = brandText,
//                                    fontWeight = FontWeight.SemiBold,
//                                    color = Color.White,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//                    }
//
//                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
//                        Column(
//                            Modifier.fillMaxWidth().weight(3.5f)
//                                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(5.dp))
//                                .padding(8.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center,
//                        ) {
//
//                            Column(Modifier.fillMaxWidth(.6f)) {
//                                Box(
//                                    Modifier.weight(.5f),
//                                    contentAlignment = Alignment.BottomStart
//                                ) {
//                                    SmartText(
//                                        modifier = Modifier.fillMaxSize()
//                                            .padding(horizontal = 5.dp),
//                                        text = "SELECT REASON:",
//                                        fontWeight = FontWeight.ExtraBold,
//                                        color = Color.Black,
//                                        textAlign = TextAlign.Center,
//                                        isAutoSetWidth = true
//                                    )
//
//                                }
//                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                                    Row(
//                                        Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                            .clip(RoundedCornerShape(5.dp))
//                                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp)),
//                                        horizontalArrangement = Arrangement.Center,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        Row(
//                                            Modifier.weight(1f).height(50.dp),
//                                            horizontalArrangement = Arrangement.Center,
//                                            verticalAlignment = Alignment.CenterVertically
//                                        ) {
//                                            BasicTextField(
//                                                modifier = Modifier.fillMaxWidth().weight(1f),
//                                                value = "${viewModel.reason}",
//                                                onValueChange = {
//                                                    viewModel.reason = it
//                                                },
//                                                keyboardOptions = KeyboardOptions.Default.copy(
//                                                    keyboardType = KeyboardType.Number
//                                                ),
//                                                textStyle = TextStyle(
//                                                    textAlign = TextAlign.Center,
//                                                    fontSize = 18.sp,
//                                                    color = Color.Black,
//                                                    fontWeight = FontWeight.Bold
//                                                ),
//                                                singleLine = true,
//                                                maxLines = 1,
//                                            )
//
//                                            Image(
//                                                modifier = Modifier.fillMaxHeight(.4f)
//                                                    .aspectRatio(1.1f),
//                                                imageVector = Icons.Default.ArrowDropDown,
//                                                contentDescription = ""
//                                            )
//                                        }
//                                    }
//                                }
//                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                                    Row(
//                                        Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                            .clip(RoundedCornerShape(5.dp))
//                                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
//                                            .padding(horizontal = 10.dp),
//                                        horizontalArrangement = Arrangement.Center,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        var creditCount by remember { mutableStateOf(0) }
//                                        Box(
//                                            Modifier.fillMaxHeight(.8f).aspectRatio(1f),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Box(
//                                                Modifier.fillMaxHeight(.8f).aspectRatio(1f).clip(
//                                                    RoundedCornerShape(5.dp)
//                                                ).background(Color.Black)
//                                                    .clickable { creditCount-- },
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                SmartText(
//                                                    modifier = Modifier.fillMaxSize()
//                                                        .padding(horizontal = 5.dp),
//                                                    text = "-",
//                                                    fontWeight = FontWeight.SemiBold,
//                                                    color = Color.White,
//                                                    textAlign = TextAlign.Center,
//                                                    downScale = 80f,
//                                                )
//                                            }
//                                        }
//                                        Box(
//                                            Modifier.weight(1f).fillMaxHeight(),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//
//                                            Box(
//                                                Modifier.fillMaxWidth().fillMaxHeight(.7f),
//                                                contentAlignment = Alignment.Center
//                                            ) {
//
//                                                SmartBasicTextField(
//                                                    modifier = Modifier.fillMaxSize(),
//                                                    text = "$creditCount",
//                                                    fontWeight = FontWeight.ExtraBold,
//                                                    color = Color.Black,
//                                                    textAlign = TextAlign.Center,
//                                                    onValueChange = {
//                                                        creditCount = it.toInt()
//                                                    })
//                                            }
//                                        }
//                                        Box(
//                                            Modifier.fillMaxHeight(.8f).aspectRatio(1f),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Box(
//                                                Modifier.fillMaxHeight(.8f).aspectRatio(1f).clip(
//                                                    RoundedCornerShape(5.dp)
//                                                ).background(Color.Black)
//                                                    .clickable { creditCount++ },
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                SmartText(
//                                                    modifier = Modifier.fillMaxSize()
//                                                        .padding(horizontal = 5.dp),
//                                                    text = "+",
//                                                    fontWeight = FontWeight.SemiBold,
//                                                    color = Color.White,
//                                                    textAlign = TextAlign.Center,
//                                                    downScale = 80f
//                                                )
//                                            }
//                                        }
//                                    }
//                                }
//                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                                    Box(
//                                        Modifier.fillMaxWidth().fillMaxHeight(.6f)
//                                            .clip(RoundedCornerShape(5.dp))
//                                            .background(Color(0xffeeeeee)),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        SmartText(
//                                            modifier = Modifier.fillMaxSize()
//                                                .padding(horizontal = 5.dp),
//                                            text = "CREDIT",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Center,
//                                        )
//                                    }
//                                }
//                            }
//
//
//                        }
//
//                        Column(
//                            Modifier.fillMaxWidth().weight(2f)
//                                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(5.dp))
//                                .padding(8.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//
//                            Column(Modifier.fillMaxWidth(.6f)) {
//                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                                    Row(
//                                        Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                            .clip(RoundedCornerShape(5.dp))
//                                            .border(
//                                                1.dp,
//                                                Color.Black,
//                                                RoundedCornerShape(5.dp)
//                                            ).padding(horizontal = 10.dp),
//                                        horizontalArrangement = Arrangement.Center,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//
//                                        Box(
//                                            Modifier.fillMaxHeight(.8f).aspectRatio(1f),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Box(
//                                                Modifier.fillMaxHeight(.8f).aspectRatio(1f).clip(
//                                                    RoundedCornerShape(5.dp)
//                                                ).background(Color.Black)
//                                                    .clickable { viewModel.missingCount-- },
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                SmartText(
//                                                    modifier = Modifier.fillMaxSize()
//                                                        .padding(horizontal = 5.dp),
//                                                    text = "-",
//                                                    fontWeight = FontWeight.SemiBold,
//                                                    color = Color.White,
//                                                    textAlign = TextAlign.Center,
//                                                    downScale = 80f
//                                                )
//                                            }
//                                        }
//                                        Box(
//                                            Modifier.weight(1f).fillMaxHeight(),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Box(
//                                                Modifier.fillMaxWidth().fillMaxHeight(.7f),
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                SmartBasicTextField(
//                                                    modifier = Modifier.fillMaxSize(),
//                                                    text = viewModel.missingCount.toString(),
//                                                    fontWeight = FontWeight.ExtraBold,
//                                                    color = Color.Black,
//                                                    textAlign = TextAlign.Center,
//                                                    onValueChange = {
//                                                        viewModel.missingCount =
//                                                            if (it.isNotBlank()) {
//                                                                it.toInt()
//                                                            } else {
//                                                                0
//                                                            }
//                                                    })
//                                            }
//
////                                            BasicTextField(
////                                                modifier = Modifier.fillMaxWidth(),
////                                                value = "$missingCount",
////                                                onValueChange = {
////                                                    missingCount = if (it.isNotBlank()) {
////                                                        it.toInt()
////                                                    } else {
////                                                        0
////                                                    }
////                                                },
////                                                keyboardOptions = KeyboardOptions.Default.copy(
////                                                    keyboardType = KeyboardType.Number
////                                                ),
////                                                textStyle = TextStyle(
////                                                    textAlign = TextAlign.Center,
////                                                    fontSize = 18.sp,
////                                                    color = Color.Black,
////                                                    fontWeight = FontWeight.Bold
////                                                ),
////                                                singleLine = true,
////                                                maxLines = 1
////                                            )
//                                        }
//                                        Box(
//                                            Modifier.fillMaxHeight(.8f).aspectRatio(1f),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Box(
//                                                Modifier.fillMaxHeight(.8f).aspectRatio(1f).clip(
//                                                    RoundedCornerShape(5.dp)
//                                                ).background(Color.Black)
//                                                    .clickable { viewModel.missingCount++ },
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                SmartText(
//                                                    modifier = Modifier.fillMaxSize()
//                                                        .padding(horizontal = 5.dp),
//                                                    text = "+",
//                                                    fontWeight = FontWeight.SemiBold,
//                                                    color = Color.White,
//                                                    textAlign = TextAlign.Center,
//                                                    downScale = 80f,
//                                                )
//                                            }
//                                        }
//                                    }
//                                }
//
//                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                                    Box(
//                                        Modifier.fillMaxWidth().fillMaxHeight(.6f)
//                                            .clip(RoundedCornerShape(5.dp))
//                                            .background(Color(0xffeeeeee)),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        SmartText(
//                                            modifier = Modifier.fillMaxSize()
//                                                .padding(horizontal = 5.dp),
//                                            text = "MISSING",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Center,
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                Row(
//                    Modifier.fillMaxWidth(.7f).weight(09f).clip(RoundedCornerShape(5.dp))
//                        .background(Color(0xffea2a25)).padding(horizontal = 20.dp),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    SmartText(
//                        modifier = Modifier.fillMaxHeight(),
//                        text = "Tote: ",
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.White,
//                        textAlign = TextAlign.Center,
//                        downScale = 50f
//                    )
//                    SmartText(
//                        modifier = Modifier.fillMaxHeight(),
//                        text = "TEST1",
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.White,
//                        textAlign = TextAlign.Center,
//                    )
//
//
//                }
//                var parentSize: IntSize by remember { mutableStateOf(IntSize.Zero) }
//                Box(
//                    modifier = Modifier.fillMaxWidth().weight(09f).background(Color(0xff07a400)).onSizeChanged { parentSize = it },
//                    contentAlignment = Alignment.Center
//                ) {
//                    var textFieldValue by remember { mutableStateOf("") }
//
//                    val defaultFontSize = (toDp(parentSize.height, 60f).value.sp).value.sp
//
//                    val focusRequester = remember { FocusRequester() }
//                    LaunchedEffect(Unit) {
//                        focusRequester.requestFocus()
//                    }
//
//                    BasicTextField(
//                        modifier = Modifier.focusRequester(focusRequester).fillMaxWidth().fillMaxHeight(.6f),
//                        value = textFieldValue,
//                        onValueChange = {
//                            textFieldValue = it
//                        },
//                        textStyle = TextStyle(
//                            textAlign = TextAlign.Center,
//                            fontSize = defaultFontSize,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        singleLine = true,
//                        maxLines = 1,
//                    ){
//                        val interactionSource = remember { MutableInteractionSource() }
//                        TextFieldDefaults.TextFieldDecorationBox(
//                            value = textFieldValue,
//                            innerTextField = it,
//                            singleLine = true,
//                            enabled = true,
//                            visualTransformation = VisualTransformation.None,
//                            leadingIcon = {
//                                Image(
//                                    modifier = Modifier.fillMaxHeight().aspectRatio(1.1f),
//                                    imageVector = MyIconPack.Logosmall,
//                                    contentDescription = ""
//                                )
//                            },
//                            interactionSource = interactionSource,
//                            contentPadding = PaddingValues(0.dp)
//                        )
//                    }
//                }
//
//                Row(
//                    Modifier.fillMaxWidth().weight(08f),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                        Row(
//                            Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                .clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//
//                            Image(
//                                modifier = Modifier.fillMaxHeight().aspectRatio(1.1f).padding(5.dp),
//                                imageVector = Icons.Rounded.KeyboardArrowLeft,
//                                contentDescription = null
//                            )
//                            SmartText(
//                                modifier = Modifier.fillMaxHeight().padding(horizontal = 5.dp),
//                                text = "PREVIOUS",
//                                fontWeight = FontWeight.ExtraBold,
//                                color = Color.Black,
//                                textAlign = TextAlign.Start,
//                                alignment = Alignment.CenterStart
//                            )
//
//                        }
//
//                    }
//                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                        Row(
//                            Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                .clip(RoundedCornerShape(5.dp)).background(Color(0xff07a400)),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            Image(
//                                modifier = Modifier.fillMaxHeight().aspectRatio(1.1f).padding(5.dp),
//                                imageVector = Icons.Rounded.Check,
//                                contentDescription = null,
//                                colorFilter = ColorFilter.tint(
//                                    Color.White
//                                )
//                            )
//                            SmartText(
//                                modifier = Modifier.fillMaxHeight().padding(horizontal = 5.dp),
//                                text = "VERIFY",
//                                fontWeight = FontWeight.ExtraBold,
//                                color = Color.White,
//                                textAlign = TextAlign.Start,
//                                alignment = Alignment.CenterStart
//                            )
//                        }
//
//                    }
//                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                        Row(
//                            Modifier.fillMaxWidth().fillMaxHeight(.8f)
//                                .clip(RoundedCornerShape(5.dp)).background(Color(0xffeeeeee)),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//
//                            SmartText(
//                                modifier = Modifier.fillMaxHeight().padding(horizontal = 5.dp),
//                                text = "SKIP",
//                                fontWeight = FontWeight.ExtraBold,
//                                color = Color.Black,
//                                textAlign = TextAlign.Start,
//                                alignment = Alignment.CenterStart
//                            )
//                            Image(
//                                modifier = Modifier.fillMaxHeight().aspectRatio(1.1f).padding(5.dp),
//                                imageVector = Icons.Rounded.KeyboardArrowRight,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//
////                Box(modifier = Modifier.weight(.06f))
//            }
//        }
//
//    }

}