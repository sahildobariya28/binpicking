package com.scanner.binpicking.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.btn_hide_password
import binpicking.shared.generated.resources.btn_show_password
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import org.jetbrains.compose.resources.painterResource

@Composable
fun SimpleTextField(hint: String, text: String, imageVector: ImageVector = Icons.Default.Email, onValueChange: (String) -> Unit) {
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
                imageVector = imageVector,
                contentDescription = "Text Field"
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        shape = RoundedCornerShape(50.dp),
        label = { Text(hint, fontSize = 14.sp) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = LightOnPrimary,
            focusedBorderColor = LightOnPrimary,
            unfocusedBorderColor = Colors.TextFieldBg,
            cursorColor = LightOnPrimary,
            backgroundColor = Colors.TextFieldBg,
            focusedLabelColor = LightOnPrimary
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun PasswordTextField(hint: String, text: String = "", isPasswordToggle: Boolean, onTextChange: (String) -> Unit, onPasswordToggleChange: (Boolean) -> Unit) {
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
                contentDescription = "Lock"
            )
        },

        trailingIcon = {
            val icon = if(isPasswordToggle){
                painterResource(Res.drawable.btn_hide_password)
            }else{
                painterResource(Res.drawable.btn_show_password)
            }
            Icon(
                painter = icon,
                contentDescription = "Password Hide",
                modifier = Modifier.fillMaxHeight()
                    .clip(CircleShape)
                    .clickable {
                        onPasswordToggleChange(!isPasswordToggle)
                    }
                    .padding(17.dp)
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
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
            textColor = LightOnPrimary,
            focusedBorderColor = LightOnPrimary,
            unfocusedBorderColor = Colors.TextFieldBg,
            cursorColor = LightOnPrimary,
            backgroundColor = Colors.TextFieldBg,
            focusedLabelColor = LightOnPrimary
        )
    )
}
