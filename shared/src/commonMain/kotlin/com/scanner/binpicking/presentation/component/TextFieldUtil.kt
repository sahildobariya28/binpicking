package com.scanner.binpicking.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.resoures.Colors
import com.scanner.binpicking.resoures.icons.Hidepassword
import com.scanner.binpicking.resoures.icons.MyIconPack
import com.scanner.binpicking.resoures.icons.Showpassword

@Composable
inline fun SimpleTextField(hint: String, text: String, crossinline onValueChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 30.dp),
        value = text,
        singleLine = true,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        shape = RoundedCornerShape(50.dp),
        label = { Text(hint, fontSize = 14.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Colors.text_theme,
            focusedBorderColor = Colors.text_theme,
            unfocusedBorderColor = Colors.text_field_bg_color,
            cursorColor = Colors.text_theme,
            backgroundColor = Colors.text_field_bg_color,
            focusedLabelColor = Colors.text_theme
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
inline fun PasswordTextField(hint: String, text: String = "", isPasswordToggle: Boolean, crossinline onTextChange: (String) -> Unit, crossinline onPasswordToggleChange: (Boolean) -> Unit) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 30.dp),
        value = text,
        singleLine = true,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            val icon = if (isPasswordToggle) {
                MyIconPack.Hidepassword
            } else {
                MyIconPack.Showpassword
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .clickable {
                        onPasswordToggleChange(!isPasswordToggle)
                    }
            )
        },
        textStyle = TextStyle(fontSize = 14.sp,),
        visualTransformation = if (isPasswordToggle) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        shape = RoundedCornerShape(50.dp),
        label = { Text(hint, fontSize = 14.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Colors.text_theme,
            focusedBorderColor = Colors.text_theme,
            unfocusedBorderColor = Colors.text_field_bg_color,
            cursorColor = Colors.text_theme,
            backgroundColor = Colors.text_field_bg_color,
            focusedLabelColor = Colors.text_theme
        )
    )
}
