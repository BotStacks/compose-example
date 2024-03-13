import sample.R
import ai.botstacks.sdk.BotStacksChat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ui.LocalToastManager
import ui.ToastManager

@Composable
fun MainView() {
    BotStacksChat.shared.setup(
        context = LocalContext.current,
        apiKey = stringResource(R.string.botstacks_api_key),
        giphyApiKey = stringResource(R.string.giphy_api_key)
    )

    CompositionLocalProvider(
        LocalToastManager provides ToastManager(LocalContext.current)
    ) {
        App()
    }
}