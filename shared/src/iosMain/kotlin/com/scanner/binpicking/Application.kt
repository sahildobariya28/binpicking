import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.scanner.binpicking.App
import com.scanner.binpicking.states.Action
import com.scanner.binpicking.store
import com.scanner.binpicking.utils.KMMContext
import com.scanner.binpicking.utils.KMMPreference
import io.github.xxfast.decompose.LocalComponentContext
import platform.UIKit.UIViewController
import platform.darwin.NSObject

fun Main(
    pref: KMMPreference,
): UIViewController = ComposeUIViewController {

    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {

            App(pref, true)
        }
    }
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}