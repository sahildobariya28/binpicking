package com.scanner.binpicking.presentation.screen.authentication


import ContentWithMessageBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightSuccessPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import rememberMessageBarState

@Composable
fun EmailConformationScreen(viewModel: EmailConformationScreenViewModel) {

    Column(
        modifier = Modifier.fillMaxSize().background(NeutralWhite),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.fillMaxWidth(.6f).padding(20.dp).aspectRatio(1.1f),
            colorFilter = ColorFilter.tint(LightOnPrimary),
            imageVector = Icons.Default.Email,
            contentDescription = "Email"
        )

        Text(
            text = "Signup Completed",
            style = MaterialTheme.typography.h6,
            color = LightSuccessPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Thank you for signing up! Your account is being created and this process may take up to 24 hours. Once your account is successfully created, we will notify you via email.",
            style = MaterialTheme.typography.body1,
            color = LightOnHint,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
        )
        Text(
            text = "The email will contain a user guide to help you get started.",
            style = MaterialTheme.typography.body1,
            color = LightOnHint,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(LightOnPrimary)
                .clickable {
                    viewModel.onNavigateToSignInScreen()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Go to Login",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}
