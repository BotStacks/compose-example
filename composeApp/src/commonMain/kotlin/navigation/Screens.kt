package navigation

import ai.botstacks.sample.kmp.screens.Example
import screens.Login
import ai.botstacks.sample.kmp.screens.MaterialRouter
import ai.botstacks.sample.kmp.screens.Splash
import screens.examples.ChatListWithHeader
import ai.botstacks.sdk.BotStacksChatController
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey

data object SplashScreen : Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val navigator = LocalPlatformNavigator.current

        Splash(
            openChat = { navigator.replaceAll(MainScreen) },
            openLogin = { navigator.replaceAll(LoginScreen) }
        )
    }
}

data object LoginScreen: Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val navigator = LocalPlatformNavigator.current
        Login { navigator.replaceAll(MainScreen) }
    }
}

data object MainScreen: Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val navigator = LocalPlatformNavigator.current

        MaterialRouter(
            onOpenExample = {
                navigator.push(ExampleContent(it))
            },
            onLogout = {
                navigator.replaceAll(LoginScreen)
            }
        )
    }
}

data class ExampleContent(val example: Example): Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val navigator = LocalPlatformNavigator.current

        when (example) {
            Example.Full -> {
                BotStacksChatController { navigator.replaceAll(LoginScreen) }
            }
            Example.ChatList_With_Header -> {
                ChatListWithHeader { navigator.pop() }
            }
        }
    }
}