package com.scanner.binpicking.presentation.screen.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.presentation.screen.homescreen.HomeScreenViewModel
import com.scanner.binpicking.resoures.Colors

@Composable
inline fun FilterDialog(viewModel: HomeScreenViewModel, crossinline onDismissFilterDialog: () -> Unit, crossinline onApplyFilter: (filterStr: String) -> Unit) {

    var noOfOrder by remember { mutableStateOf("") }
    var pickingStatus by remember { mutableStateOf("") }
    var pickingEmployee by remember { mutableStateOf("") }
    var filterByTime by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 15.dp))) {
        Box(
            Modifier.fillMaxWidth().height(60.dp).background(Colors.background)
                .padding(start = 10.dp), contentAlignment = Alignment.CenterStart
        ) {

            Box(
                modifier = Modifier.size(45.dp).clickable {
                    onDismissFilterDialog()
                }, contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
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
            value = noOfOrder,
            singleLine = true,
            onValueChange = {
                noOfOrder = it
            },
            textStyle = TextStyle(fontSize = 14.sp),
            shape = RoundedCornerShape(10.dp),
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
                .background(Colors.text_field_bg_color)
                .clickable { isPickingStatusDropDown = !isPickingStatusDropDown },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f).padding(start = 20.dp),
                text = pickingStatus,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).padding(8.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Colors.text_black
            )
            DropdownMenu(
                expanded = isPickingStatusDropDown,
                onDismissRequest = { isPickingStatusDropDown = false },
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            ) {

                DropdownMenuItem(onClick = {
                    pickingStatus = "None"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "None")
                }
                DropdownMenuItem(onClick = {
                    pickingStatus = "Completed"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "Completed")
                }
                DropdownMenuItem(onClick = {
                    pickingStatus = "Pending"
                    isPickingStatusDropDown = false
                }) {
                    Text(text = "Pending")
                }
                DropdownMenuItem(onClick = {
                    pickingStatus = "Processing"
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
            value = pickingEmployee,
            singleLine = true,
            onValueChange = {
                pickingEmployee = it
            },
            textStyle = TextStyle(fontSize = 14.sp),
            shape = RoundedCornerShape(10.dp),
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
                .background(Colors.text_field_bg_color)
                .clickable { isAscDecDropDown = !isAscDecDropDown },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f).padding(start = 15.dp),
                text = filterByTime,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).padding(8.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Colors.text_black
            )

            DropdownMenu(
                expanded = isAscDecDropDown,
                onDismissRequest = { isAscDecDropDown = false },
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            ) {

                DropdownMenuItem(onClick = {
                    filterByTime = "None"
                    isAscDecDropDown = false
                }) {
                    Text(text = "None")
                }
                DropdownMenuItem(onClick = {
                    filterByTime = "Ascending"
                    isAscDecDropDown = false
                }) {
                    Text(text = "Ascending")
                }
                DropdownMenuItem(onClick = {
                    filterByTime = "Descending"
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
            .background(Colors.orange)
            .clickable {
                var filterStr = ""
                if (filterByTime.equals("Ascending")) filterStr = "$filterStr&filter[starting_time]=ASC"
                if (filterByTime.equals("Descending")) filterStr = "$filterStr&filter[starting_time]=DESC"
                if (noOfOrder.isNotEmpty()) filterStr = "$filterStr&filter[orders]=$noOfOrder"
                if (pickingStatus.equals("Completed")) filterStr = "$filterStr&filter[status]=1"
                if (pickingStatus.equals("Pending")) filterStr = "$filterStr&filter[status]=0"
                if (pickingStatus.equals("Processing")) filterStr = "$filterStr&filter[status]=2"
                if (pickingEmployee.isNotEmpty()) filterStr = "$filterStr&filter[employee]=$pickingEmployee"
                onApplyFilter(filterStr)
            }, contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Apply Filter",
                fontSize = 18.sp,
                color = Colors.white,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.fillMaxWidth().height(20.dp))
    }

}