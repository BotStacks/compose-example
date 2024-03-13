import ai.botstacks.sdk.ui.BotStacksThemeEngine
import androidx.compose.runtime.*
import navigation.AppNavigation

@Composable
fun App() {
    BotStacksThemeEngine {
        AppNavigation()
    }
}