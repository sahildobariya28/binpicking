package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.domain.mode.MultiPickingData
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.BtnDelete
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.utils.ActionType
import com.scanner.binpicking.utils.SoundMediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
inline fun CreateMultiPickingDialog(
    viewModel: HomeScreenViewModel,
    crossinline onDismissMultiPickingDialog: () -> Unit,
    crossinline onEnterButtonClick: (noOfOrder: Int) -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Colors.white).clickable(enabled = false) { },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            Modifier.fillMaxWidth().height(60.dp).background(Colors.background)
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f).padding(start = 15.dp),
                text = "Number Order",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Box(
                modifier = Modifier.size(45.dp).clickable {
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
        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Colors.text_field_bg_color))

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(
                top = 20.dp,
                bottom = 50.dp,
                start = 20.dp,
                end = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.multiOrderList) {
                Column {
                    Row(
                        Modifier.fillMaxWidth().height(55.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var orderNo by remember { mutableStateOf(TextFieldValue("")) }
                        Row(
                            Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            BasicTextField(
                                modifier = Modifier.weight(1f)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    ).background(Colors.background),
                                value = orderNo.text,
                                onValueChange = { orderNo = TextFieldValue(it) })

                            Box(
                                modifier = Modifier.weight(1f)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    ).background(Colors.background)
                            ) {

                            }
                        }
                        Box(
                            Modifier.padding(start = 10.dp).size(50.dp).clickable {
                                viewModel.multiOrderList.remove(it)
                            },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = MyIconPack.BtnDelete,
                                contentDescription = null
                            )
                        }

                    }
                    Spacer(
                        Modifier.padding(top = 12.dp).fillMaxWidth().height(2.dp)
                            .background(Colors.background)
                    )
                }

            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Box(
                Modifier.padding(horizontal = 8.dp, vertical = 10.dp).height(45.dp)
                    .clip(RoundedCornerShape(10.dp)).background(Color(0xFFF4E5DD)).clickable {
                        viewModel.multiOrderList.add(MultiPickingData())
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "Add",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Colors.orange,
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                Modifier.padding(horizontal = 8.dp, vertical = 10.dp).height(45.dp)
                    .clip(RoundedCornerShape(10.dp)).background(Colors.orange).clickable {

                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "Enter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}