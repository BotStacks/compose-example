package ai.botstacks.sample.ui

import ai.botstacks.sample.screens.Login
import ai.botstacks.sample.screens.Splash
import ai.botstacks.sample.screens.examples.ChatListWithHeader
import ai.botstacks.sample.screens.Example
import ai.botstacks.sample.screens.MaterialRouter
import ai.botstacks.sdk.BotStacksChatController
import ai.botstacks.sdk.ui.BotStacksThemeEngine
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                BotStacksThemeEngine {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash") {
                        val openBotstacks = { navController.navigate("router") }
                        val openLogin = { navController.navigate("login") }

                        composable("splash") {
                            Splash(openChat = openBotstacks, openLogin = openLogin)
                        }
                        composable("login") {
                            Login(openBotstacks)
                        }

                        composable("router") {
                            MaterialRouter(
                                onOpenExample = { navController.navigate(it.route) },
                                onLogout = { navController.navigate("login") }
                            )
                        }

                        composable(Example.Full.route) {
                            BotStacksChatController { navController.navigate("login") }
                        }

                        composable(Example.ChatList_With_Header.route) {
                            ChatListWithHeader { navController.navigateUp() }
                        }
                    }
                }
            }
        }
    }
}