package com.scanner.binpicking.presentation.screen.authentication.component

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.util.autoFillRequestHandler
import com.scanner.binpicking.util.connectNode
import com.scanner.binpicking.util.defaultFocusChangeAutoFill

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInTextField(
    hint: String,
    text: String,
    imageVector: ImageVector = Icons.Default.Email,
    onValueChange: (String) -> Unit
) {
    val autofillEmail =
        autoFillRequestHandler(
            autofillTypes = listOf(AutofillType.Username),
            onFill = { onValueChange(it) }
        )

    OutlinedTextField(
        modifier = Modifier
            .connectNode(handler = autofillEmail)
            .defaultFocusChangeAutoFill(handler = autofillEmail)
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
                contentDescription = "Email"
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