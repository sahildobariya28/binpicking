package com.scanner.binpicking.presentation.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import binpicking.shared.generated.resources.Res
import binpicking.shared.generated.resources.btn_close
import binpicking.shared.generated.resources.img_signout
import com.scanner.binpicking.theme.Colors.LightOnHint
import com.scanner.binpicking.theme.Colors.LightOnPrimary
import com.scanner.binpicking.theme.Colors.LightPrimary
import com.scanner.binpicking.theme.Colors.NeutralWhite
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignOutConfirmationDialog(onConfirmSignOutListener: () -> Unit, onCloseSignOutDialog: () -> Unit) {

    Dialog(onDismissRequest = {
        onCloseSignOutDialog()
    }) {
        Column(
            Modifier.width(IntrinsicSize.Max).clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Image(
                        modifier = Modifier.size(40.dp).offset(x = 10.dp, y = (-10).dp)
                            .clickable { onCloseSignOutDialog() }
                            .padding(8.dp),
                        painter = painterResource(Res.drawable.btn_close),
                        contentDescription = "Close Button")
                }

                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(Res.drawable.img_signout),
                    contentDescription = "Image SignOut"
                )
            }

            Spacer(Modifier.fillMaxWidth().height(15.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "SignOut",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LightOnPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.fillMaxWidth().height(10.dp))
            Box(Modifier.width(80.dp).height(2.dp).clip(CircleShape).background(LightPrimary))
            Spacer(Modifier.fillMaxWidth().height(10.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
                text = "Are you sure.\nYou want to SignOut your Account ?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = LightOnHint,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.fillMaxWidth().height(20.dp))

            Box(
                Modifier.fillMaxWidth(.7f).height(45.dp).clip(RoundedCornerShape(5.dp))
                    .background(LightPrimary).clickable {
                        onConfirmSignOutListener()
                    }, contentAlignment = Alignment.Center
            ) {
                Text(text = "Sign Out", color = NeutralWhite)
            }
        }
    }
}