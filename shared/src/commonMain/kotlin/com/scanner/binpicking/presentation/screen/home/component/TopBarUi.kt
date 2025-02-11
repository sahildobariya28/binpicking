package com.scanner.binpicking.presentation.screen.home.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.btn_filter
import binpicking.shared.generated.resources.btn_sign_out
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.AppConfig
import com.scanner.binpicking.presentation.screen.home.HomeViewModel
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.LightSecondary
import com.scanner.binpicking.theme.Colors.NeutralBlack
import com.scanner.binpicking.theme.Colors.NeutralWhite
import com.scanner.binpicking.topSafeArea
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopBarUi(
    viewModel: HomeViewModel,
    onFilterClickListener: () -> Unit,
    onSignOutClickListener: () -> Unit,
    onApplyFilter: (filterStr: String) -> Unit
) {
    Box(
        Modifier.fillMaxWidth().background(LightPrimary).padding(top = topSafeArea.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
                        .clickable {
                            onSignOutClickListener()
                        }.padding(15.dp),
                    painter = painterResource(Res.drawable.btn_sign_out),
                    contentDescription = "Menu",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))


                if (AppConfig.isAdministrator()) {
                    Icon(
                        modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
                            .clickable { onFilterClickListener() }
                            .padding(15.dp),
                        painter = painterResource(Res.drawable.btn_filter),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(.7f).height(50.dp).clip(RoundedCornerShape(10.dp))
                .background(NeutralWhite),
            contentAlignment = Alignment.Center
        ) {
            var isPickingStatusDropDown by remember { mutableStateOf(false) }
            Row(Modifier.fillMaxSize()) {
                var pickingStatus by remember { mutableStateOf("Orders Number") }
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
                        modifier = Modifier.width(30.dp).fillMaxHeight().clip(RoundedCornerShape(10.dp)),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Drop Down",
                        tint = NeutralBlack
                    )
                    DropdownMenu(
                        expanded = isPickingStatusDropDown,
                        onDismissRequest = { isPickingStatusDropDown = false },
                        modifier = Modifier.width(IntrinsicSize.Min).padding(horizontal = 15.dp)
                            .fillMaxWidth()
                    ) {
                        DropdownMenuItem(onClick = {
                            pickingStatus = "Orders Number"
                            isPickingStatusDropDown = false
                        }) {
                            Text(text = "Orders Number")
                        }

                        if (AppConfig.isAdministrator()) {
                            DropdownMenuItem(onClick = {
                                pickingStatus = "Picker Name"
                                isPickingStatusDropDown = false
                            }) {
                                Text(text = "Picker Name")
                            }
                        }

                    }
                }

                Spacer(Modifier.fillMaxHeight().width(1.dp).background(LightPrimary))


                TextField(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    value = viewModel.searchText,
                    singleLine = true,
                    onValueChange = {
                        viewModel.searchText = it
                    },
                    keyboardActions = KeyboardActions(onDone = {
                        var filterStr = ""

                        if (pickingStatus == "Orders Number") {
                            if (viewModel.searchText.text.isNotEmpty()) filterStr =
                                "$filterStr&filter[orders]=${viewModel.searchText.text}"
                        } else if (pickingStatus == "Picker Name") {
                            if (viewModel.searchText.text.isNotEmpty()) filterStr =
                                "$filterStr&filter[employee]=${viewModel.searchText.text}"
                        } else if (pickingStatus == "Customer Name") {
                            if (viewModel.searchText.text.isNotEmpty()) filterStr =
                                "$filterStr&filter[employee]=${viewModel.searchText.text}"
                        }
                        onApplyFilter(filterStr)
                    }),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                var filterStr = ""

                                if (pickingStatus == "Orders Number") {
                                    if (viewModel.searchText.text.isNotEmpty()) filterStr =
                                        "$filterStr&filter[orders]=${viewModel.searchText.text}"
                                } else if (pickingStatus == "Picker Name" || pickingStatus == "Customer Name") {
                                    if (viewModel.searchText.text.isNotEmpty()) filterStr =
                                        "$filterStr&filter[employee]=${viewModel.searchText.text}"
                                }
                                onApplyFilter(filterStr)
                            },
                            modifier = Modifier.size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Red)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    },
                    textStyle = TextStyle(fontSize = 15.sp),
                    label = {
                        Text(
                            "Search by $pickingStatus",
                            maxLines = 1,
                            fontSize = 12.sp,
                            color = LightOnHint
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = LightOnPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = LightSecondary,
                        backgroundColor = Colors.TextFieldBg,
                        focusedLabelColor = LightOnHint.copy(alpha = .5f),
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )
            }
        }
    }
}