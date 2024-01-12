package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.BtnMinus
import com.scanner.binpicking.resoures.icons.BtnPlus
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.utils.ActionType
import com.scanner.binpicking.utils.SoundMediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
//    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = .5f))
//        .clickable(
//            indication = null,
//            interactionSource = remember { MutableInteractionSource() }) {
//            onDismissMultiPickingDialog()
//        }, contentAlignment = Alignment.BottomCenter
//    ) {
inline fun SelectMultiPickingCountDialog(
    homeViewModel: HomeScreenViewModel,
    crossinline onDismissMultiPickingDialog: () -> Unit,
    crossinline onEnterButtonClick: (noOfOrder: Int) -> Unit,
) {

    var noOfOrder by remember { mutableStateOf(0) }

    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Colors.white).clickable(enabled = false) { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            Modifier.fillMaxWidth().height(60.dp).background(Colors.background)
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = "Number Order",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Box(
                modifier = Modifier.size(50.dp).clickable {
                    onDismissMultiPickingDialog()
                }, contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }

        }

        Spacer(Modifier.fillMaxWidth().height(10.dp))

        Column(
            Modifier.fillMaxWidth(.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                Modifier.padding(horizontal = 10.dp).height(50.dp)
                    .clip(RoundedCornerShape(10.dp)).border(
                        1.dp, Colors.black, RoundedCornerShape(10.dp)
                    ).background(Color.White).padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(30.dp).clickable {
                            if (noOfOrder > 0) {
                                noOfOrder--
                            }
                        }, imageVector = MyIconPack.BtnMinus, contentDescription = null
                    )

                    Box(
                        Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier.fillMaxWidth().fillMaxHeight(.7f),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "${noOfOrder}",
                                onValueChange = {
                                    if (it.isNotBlank()) {
                                        noOfOrder = it.toInt()
                                    } else {
                                        noOfOrder = 0
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Decimal // Allow any number input
                                ),
                                textStyle = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                ),
                                singleLine = true,
                                maxLines = 1
                            )
                        }
                    }

                    Image(
                        modifier = Modifier.size(30.dp).clickable {
                            noOfOrder++
                        }, imageVector = MyIconPack.BtnPlus, contentDescription = null
                    )
                }
            }

            Box(
                Modifier.padding(horizontal = 10.dp).fillMaxWidth().height(45.dp)
                    .clip(RoundedCornerShape(10.dp)).background(Colors.orange).clickable {
                        onEnterButtonClick(noOfOrder)
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = "Enter",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Spacer(Modifier.fillMaxWidth().height(10.dp))
    }
//    }
}