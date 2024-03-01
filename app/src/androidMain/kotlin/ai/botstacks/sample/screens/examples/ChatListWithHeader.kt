package ai.botstacks.sample.screens.examples

import ai.botstacks.sample.R
import ai.botstacks.sample.ui.theme.Purple40
import ai.botstacks.sample.ui.theme.PurpleGrey80
import ai.botstacks.sdk.ui.BotStacks
import ai.botstacks.sdk.ui.BotStacksThemeEngine
import ai.botstacks.sdk.ui.components.ChatList
import ai.botstacks.sdk.ui.components.Header
import ai.botstacks.sdk.ui.theme.lightBotStacksColors
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

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
        assets = BotStacks.assets.copy(
            logo = R.drawable.ic_launcher_foreground
        )
    ) {
        val context = LocalContext.current
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
                Toast.makeText(
                    context,
                    "chat named ${chat.displayName} clicked",
                    Toast.LENGTH_SHORT
                ).show()
            },
        )
    }
}