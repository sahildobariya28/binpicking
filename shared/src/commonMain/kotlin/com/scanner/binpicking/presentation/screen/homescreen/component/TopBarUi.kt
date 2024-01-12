package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.Filter
import com.scanner.binpicking.resoures.icons.Logobiglight
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.resoures.icons.Signout
import com.scanner.binpicking.topSafeArea

@Composable
fun TopBarUi(
    viewModel: HomeScreenViewModel,
    onFilterClickListener: () -> Unit,
    onSignOutClickListener: () -> Unit,
    onApplyFilter: (filterStr: String) -> Unit
) {


    Box(
        Modifier.fillMaxWidth().background(Colors.theme).padding(top = topSafeArea.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
                        .clickable {
                            onSignOutClickListener()
//                            mainScreenViewModel.navigateClick = !mainScreenViewModel.navigateClick
                        }.padding(15.dp),
                    imageVector = MyIconPack.Signout,
                    contentDescription = "Menu",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))

                // Search icon button
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp).size(50.dp).padding(5.dp)
                        .clickable {
                            onFilterClickListener()

                        }
                        .padding(12.dp),
                    imageVector = MyIconPack.Filter,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(.7f).height(50.dp).clip(RoundedCornerShape(10.dp)).background(Colors.white),
            contentAlignment = Alignment.Center
        ) {
            var isPickingStatusDropDown by remember { mutableStateOf(false) }
            Row(Modifier.fillMaxSize()) {
                var pickingStatus by remember { mutableStateOf("Number Of Orders") }
                Row(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .fillMaxHeight()
                        .clickable { isPickingStatusDropDown = !isPickingStatusDropDown },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f).padding(start = 15.dp),
                        text = pickingStatus,
                        minLines = 1,
                        maxLines = 1,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Icon(
                        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp))
                            .padding(5.dp),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Colors.text_black
                    )
                    DropdownMenu(
                        expanded = isPickingStatusDropDown,
                        onDismissRequest = { isPickingStatusDropDown = false },
                        modifier = Modifier.width(IntrinsicSize.Min).padding(horizontal = 15.dp)
                            .fillMaxWidth()
                    ) {

                        DropdownMenuItem(onClick = {
                            pickingStatus = "Number Of Orders"
                            isPickingStatusDropDown = false
                        }) {
                            Text(text = "Number Of Orders")
                        }
                        DropdownMenuItem(onClick = {
                            pickingStatus = "Picker Name"
                            isPickingStatusDropDown = false
                        }) {
                            Text(text = "Picker Name")
                        }
                        DropdownMenuItem(onClick = {
                            pickingStatus = "Customer Name"
                            isPickingStatusDropDown = false
                        }) {
                            Text(text = "Customer Name")
                        }
                    }
                }

                Spacer(Modifier.fillMaxHeight().width(1.dp).background(Colors.theme))


                TextField(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    value = viewModel.searchText,
                    singleLine = true,
                    onValueChange = {
                        viewModel.searchText = it
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp))
                                .background(Color.Red).clickable {

                                    var filterStr = ""

                                    if (pickingStatus.equals("Number Of Orders")){
                                        if (viewModel.searchText.isNotEmpty()) filterStr = "$filterStr&filter[orders]=$viewModel.searchText"
                                    }else if (pickingStatus.equals("Picker Name")){
                                        if (viewModel.searchText.isNotEmpty()) filterStr = "$filterStr&filter[employee]=$viewModel.searchText"
                                    }else if (pickingStatus.equals("Customer Name")){
                                        if (viewModel.searchText.isNotEmpty()) filterStr = "$filterStr&filter[employee]=$viewModel.searchText"
                                    }
                                    onApplyFilter(filterStr)

                                }.padding(8.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    textStyle = TextStyle(fontSize = 15.sp),
                    label = {
                        Text(
                            "Search $pickingStatus",
                            fontSize = 12.sp,
                            color = Colors.text_hint
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Colors.theme,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Colors.theme,
                        backgroundColor = Colors.text_field_bg_color,
                        focusedLabelColor = Colors.text_hint.copy(alpha = .5f),
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )


            }

        }
    }
}