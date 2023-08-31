package io.inappchat.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.inappchat.sdk.ui.InAppChatContext
import io.inappchat.sdk.ui.InAppChatRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                InAppChatContext {
                    NavHost(navController = navController, startDestination = "splash") {
                        val openChat = {
                            navController.navigate("chats")
                        }
                        composable("splash") {
                            Splash(openChat = openChat, openLogin = {
                                navController.navigate("login")
                            })
                        }
                        composable("login") {
                            Login(openChat)
                        }
                        InAppChatRoutes(navController = navController, navGraphBuilder = this)
                    }
                }
            }
        }
    }
}