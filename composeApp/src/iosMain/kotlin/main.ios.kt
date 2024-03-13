import ai.botstacks.sdk.BotStacksChat
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import ui.LocalToastManager
import ui.ToastManager
import util.readPlist

fun MainViewController() = ComposeUIViewController(
    configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) {
    val apiKey = readPlist<String>("AppSecrets", "BOTSTACKS_API_KEY") ?: throw IllegalArgumentException("BotStacks API Key not provided")

    BotStacksChat.shared.setup(
        apiKey = apiKey,
    )

    CompositionLocalProvider(
        LocalToastManager provides ToastManager()
    ) {
        App()
    }
}