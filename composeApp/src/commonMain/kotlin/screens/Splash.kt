package ai.botstacks.sample.kmp.screens

import ai.botstacks.sdk.BotStacksChat
import ai.botstacks.sdk.ui.components.Spinner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Splash(openChat: () -> Unit, openLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
//            Image(
//                painter = painterResource(Res.drawable.ic_launcher_foreground),
//                contentDescription = "BotStacks",
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier
//                    .background(Color(0xFFEBEDFF), CircleShape)
//            )
            Text("Simple and elegant chat services", fontSize = 20.sp, color = Color.White)
            if (!BotStacksChat.shared.loaded || BotStacksChat.shared.loggingIn)
                Spinner()
        }
    }

    LaunchedEffect(BotStacksChat.shared.loaded) {
        if (BotStacksChat.shared.loaded) {
            if (BotStacksChat.shared.isUserLoggedIn) {
                openChat()
            } else {
                openLogin()
            }
        }
    }
}