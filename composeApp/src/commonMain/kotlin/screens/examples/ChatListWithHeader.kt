package screens.examples

import ai.botstacks.sdk.ui.BotStacksThemeEngine
import ai.botstacks.sdk.ui.components.ChatList
import ai.botstacks.sdk.ui.components.Header
import ai.botstacks.sdk.ui.theme.lightBotStacksColors
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.LocalToastManager
import ui.theme.Purple40
import ui.theme.PurpleGrey80

@Composable
fun ChatListWithHeader(onBackClicked: () -> Unit) {
    BotStacksThemeEngine(
        useDarkTheme = false,
        lightColorScheme = lightBotStacksColors(
            primary = Purple40,
            onPrimary = Color.Black,
            header = PurpleGrey80,
            onHeader = Color.Black
        ),
//        assets = Assets().apply { logoPainter = painterResource(Res.drawable.ic_launcher_foreground) }
    ) {
        val toaster = LocalToastManager.current
        ChatList(
            header = {
                Header(onBackClicked = onBackClicked,
                    menu  = {
                        item {
                            Text(modifier = Modifier.padding(it), text = "Hi")
                        }
                    }
                )
            },
            emptyState = {
                Text(text = "Nothing here")
            },
            onChatClicked = { chat ->
                toaster?.toast( "chat named ${chat.displayName} clicked")
            },
        )
    }
}