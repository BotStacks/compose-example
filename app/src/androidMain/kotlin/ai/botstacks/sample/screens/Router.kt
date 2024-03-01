package ai.botstacks.sample.screens

import ai.botstacks.sdk.BotStacksChat
import ai.botstacks.sdk.ui.BotStacks
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

enum class Example(val route: String) {
    Full("botstacks_full"),
    ChatList_With_Header("chat_list_header")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialRouter(
    onOpenExample: (Example) -> Unit,
    onLogout: () -> Unit,
) {
    BotStacksChat.shared.onLogout = onLogout

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = { },
            actions = {
                IconButton(onClick = { BotStacksChat.logout() }) {
                    Icon(Icons.AutoMirrored.Rounded.Logout, contentDescription = "Log out")
                }
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(Example.entries) { index, ex ->
                ExampleRow(example = ex) {
                    onOpenExample(ex)
                }

                if (index < Example.entries.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
            }
        }
    }
}

@Composable
private fun ExampleRow(
    modifier: Modifier = Modifier,
    example: Example,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(
                horizontal = BotStacks.dimens.inset,
                vertical = BotStacks.dimens.grid.x3
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val title = when (example) {
            Example.Full -> "Controller"
            Example.ChatList_With_Header -> "ChatList with Header"
        }

        val description = when (example) {
            Example.Full -> "Example using our drop-in BotStacksChat Controller"
            Example.ChatList_With_Header -> "Example of Header being customized with a chat list."
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = BotStacks.fonts.h3.asTextStyle())
            Text(text = description, style = BotStacks.fonts.body1.asTextStyle(), minLines = 2)
        }
        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
    }
}