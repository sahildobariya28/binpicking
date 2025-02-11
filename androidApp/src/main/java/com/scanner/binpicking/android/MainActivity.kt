package com.scanner.binpicking.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.retainedComponent
import com.scanner.binpicking.Application
import com.scanner.binpicking.navigation.RootComponent
import com.scanner.binpicking.theme.Colors
import com.scanner.binpicking.util.BrowserWrapper
import com.scanner.binpicking.util.KMMPreference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Colors.LightPrimary.toArgb()
        window.navigationBarColor = Colors.LightPrimary.toArgb()

        val root = retainedComponent {
            RootComponent(it)
        }
        setContent {

            val pref = KMMPreference(this.application)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Application(root, BrowserWrapper(this), pref, false)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
