package io.inappchat.sample

import android.Manifest
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.messaging.FirebaseMessaging
import io.inappchat.sdk.InAppChat
import io.inappchat.sdk.ui.InAppChatContext
import io.inappchat.sdk.ui.views.Spinner
import io.inappchat.sdk.utils.launch
import timber.log.Timber

@Composable
fun Login(openChat: () -> Unit) {
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                it.result?.let {
                    InAppChat.registerFCMToken(it)
                }
            }.addOnFailureListener {
                Timber.tag("Login").e(it.stackTraceToString())
            }
        }
        openChat()
    }
    val activity = LocalContext.current as Activity
    Splash(openLogin = {}, openChat = openChat) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            ElevatedButton(onClick = {
                Log.v("InAppChat-Sample", "Login Click")
                launch {
                    App.app.login(activity)
                    if (InAppChat.shared.isUserLoggedIn) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }, enabled = !InAppChat.shared.loggingIn, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    MaterialTheme {
        Login(openChat = {})
    }
}