package com.scanner.binpicking.presentation.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
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
import com.scanner.binpicking.presentation.screen.home.HomeViewModel
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightBackground
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightOnSecondary
import com.scanner.binpicking.theme.Colors.NeutralBlack
import com.scanner.binpicking.theme.Colors.NeutralOrange
import com.scanner.binpicking.theme.Colors.NeutralWhite

@Composable
fun FilterSideBar(viewModel: HomeViewModel, onDismissFilterDialog: () -> Unit, onApplyFilter: (filterStr: String) -> Unit) {

    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 15.dp))) {
        Box(
            Modifier.fillMaxWidth().height(60.dp).background(LightBackground)
                .padding(start = 10.dp), contentAlignment = Alignment.CenterStart
        ) {

            Box(
                modifier = Modifier.size(45.dp).clickable {
                    onDismissFilterDialog()
                }, contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }

            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Filter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }


        }
        Spacer(Modifier.fillMaxWidth().height(20.dp))

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
            text = "Number Of Orders",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.fillMaxWidth().height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(55.dp)
                .padding(horizontal = 20.dp),
            value = viewModel.noOfOrder,
            singleLine = true,
            onValueChange = {
                viewModel.noOfOrder = it
            },
            textStyle = TextStyle(fontSize = 15.sp,fontWeight = FontWeight.SemiBold),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = LightOnPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LightOnSecondary,
                backgroundColor = Colors.TextFieldBg,
                focusedLabelColor = LightOnHint.copy(alpha = .5f),
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
            text = "Picking Status",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.fillMaxWidth().height(10.dp))
        var isPickingStatusDropDown by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(55.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Colors.TextFieldBg)
                .clickable { isPickingStatusDropDown = !isPickingStatusDropDown },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f).padding(start = 20.dp),
                text = viewModel.pickingStatus,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).padding(8.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Arrow Drop Down",
                tint = NeutralBlack
            )
            DropdownMenu(
                expanded = isPickingStatusDropDown,
                onDismissRequest = { isPickingStatusDropDown = false },
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            ) {

                DropdownMenuItem(onClick = {
                    viewModel.pickingStatus = "None"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "None")
                }
                DropdownMenuItem(onClick = {
                    viewModel.pickingStatus = "Completed"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "Completed")
                }
                DropdownMenuItem(onClick = {
                    viewModel.pickingStatus = "Pending"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "Pending")
                }
                DropdownMenuItem(onClick = {
                    viewModel.pickingStatus = "Processing"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "Processing")
                }
            }
        }


        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
            text = "Picking Employee",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.fillMaxWidth().height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(55.dp)
                .padding(horizontal = 20.dp),
            value = viewModel.pickingEmployee,
            singleLine = true,
            onValueChange = {
                viewModel.pickingEmployee = it
            },
            textStyle = TextStyle(fontSize = 15.sp,  fontWeight = FontWeight.SemiBold),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = LightOnPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LightOnSecondary,
                backgroundColor = Colors.TextFieldBg,
                focusedLabelColor = LightOnHint.copy(alpha = .5f),
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
            text = "Picking Start Time",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.fillMaxWidth().height(10.dp))
        var isAscDecDropDown by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(55.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Colors.TextFieldBg)
                .clickable { isAscDecDropDown = !isAscDecDropDown },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f).padding(start = 15.dp),
                text = viewModel.filterByTime,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).padding(8.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop Down Arrow",
                tint = NeutralBlack
            )

            DropdownMenu(
                expanded = isAscDecDropDown,
                onDismissRequest = { isAscDecDropDown = false },
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            ) {

                DropdownMenuItem(onClick = {
                    viewModel.filterByTime = "None"
                    isAscDecDropDown = false
                }) {
                    Text(text = "None")
                }
                DropdownMenuItem(onClick = {
                    viewModel.filterByTime = "Ascending"
                    isAscDecDropDown = false
                }) {
                    Text(text = "Ascending")
                }
                DropdownMenuItem(onClick = {
                    viewModel.filterByTime = "Descending"
                    isAscDecDropDown = false
                }) {
                    Text(text = "Descending")
                }
            }
        }
        Spacer(Modifier.fillMaxWidth().height(20.dp))
        Box(Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(55.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(NeutralOrange)
            .clickable {
                var filterStr = ""
                if (viewModel.filterByTime == "Ascending") filterStr = "$filterStr&filter[starting_time]=ASC"
                if (viewModel.filterByTime == "Descending") filterStr = "$filterStr&filter[starting_time]=DESC"
                if (viewModel.noOfOrder.isNotEmpty()) filterStr = "$filterStr&filter[orders]=${viewModel.noOfOrder}"
                if (viewModel.pickingStatus == "Completed") filterStr = "$filterStr&filter[status]=1"
                if (viewModel.pickingStatus == "Pending") filterStr = "$filterStr&filter[status]=0"
                if (viewModel.pickingStatus == "Processing") filterStr = "$filterStr&filter[status]=2"
                if (viewModel.pickingEmployee.isNotEmpty()) filterStr = "$filterStr&filter[employee]=${viewModel.pickingEmployee}"
                onApplyFilter(filterStr)
            }, contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Apply Filter",
                fontSize = 18.sp,
                color = NeutralWhite,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.fillMaxWidth().height(20.dp))
    }

}