package ai.botstacks.sample.screens

import ai.botstacks.sample.service.AutoFillRequestHandler
import ai.botstacks.sample.ui.components.BrowserModal
import ai.botstacks.sample.service.connectNode
import ai.botstacks.sample.service.defaultFocusChangeAutoFill
import ai.botstacks.sdk.BotStacksChat
import ai.botstacks.sdk.ui.BotStacks.colorScheme
import ai.botstacks.sdk.ui.BotStacksThemeEngine
import android.Manifest
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
fun isValidEmail(email: String): Boolean {
    return email.matches(emailRegex)
}


@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun Login(openChat: () -> Unit) {
    BotStacksThemeEngine(useDarkTheme = true) {

        val scope = rememberCoroutineScope()

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                scope.launch {
                    val result = FirebaseMessaging.getInstance().token()
                    result.getOrNull()?.let {
                        BotStacksChat.registerFCMToken(it)
                    }
                }
            }
            openChat()
        }

        val terms = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = terms
        )

        BackHandler(enabled = terms.isVisible) {
            scope.launch { terms.hide() }
        }

        var email by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }

        val passwordFocus = remember { FocusRequester() }
        val canLogin = isValidEmail(email.text) && password.text.length > 4
        val login = {
            if (canLogin) {
                scope.launch {
                    BotStacksChat.shared.login(email.text.hashCode().toString(), email.text)
                    if (BotStacksChat.shared.isUserLoggedIn) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }
        }


        BottomSheetScaffold(
            modifier = Modifier
                .background(colorScheme.background)
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize(),
            sheetPeekHeight = 0.dp,
            sheetContent = {
                BrowserModal(url = "https://botstacks.ai/terms-of-service") {
                    scope.launch { terms.hide() }
                }
            },
            scaffoldState = scaffoldState
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom)
            ) {
                item {
                    Logo()
                    Spacer(modifier = Modifier.size(60.dp))
                    val autofillEmail =
                        AutoFillRequestHandler(autofillTypes = listOf(AutofillType.EmailAddress),
                            onFill = { email = TextFieldValue(it) }
                        )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .connectNode(handler = autofillEmail)
                            .defaultFocusChangeAutoFill(handler = autofillEmail),
                        value = email,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                        ),
                        onValueChange = { email = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = { passwordFocus.captureFocus() }),
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    val autofillPassword =
                        AutoFillRequestHandler(autofillTypes = listOf(AutofillType.Password),
                            onFill = { password = TextFieldValue(it) }
                        )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .connectNode(autofillPassword)
                            .defaultFocusChangeAutoFill(autofillPassword),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        value = password,
                        onValueChange = { password = it },
                    )

                    ElevatedButton(
                        onClick = login,
                        enabled = !BotStacksChat.shared.loggingIn && canLogin,
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = colorScheme.primary,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(text = "Login")
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clickable {
                            }, horizontalArrangement = Arrangement.Center
                    ) {
                        val annotatedString = buildAnnotatedString {
                            append("Don't have an account? ")

                            pushStringAnnotation(
                                tag = "register",
                                annotation = ""
                            )
                            withStyle(
                                style = SpanStyle(
                                    color = colorScheme.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Create one")
                            }
                        }
                        Text(
                            annotatedString,
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                scope.launch { terms.show() }
                            }, horizontalArrangement = Arrangement.Center
                    ) {
                        val annotatedString = buildAnnotatedString {
                            append("By logging in, you agree to the InAppChat ")

                            pushStringAnnotation(
                                tag = "terms of service",
                                annotation = "https://google.com/policy"
                            )
                            withStyle(
                                style = SpanStyle(
                                    color = colorScheme.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("terms of service")
                            }
                        }
                        Text(
                            annotatedString,
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

private suspend fun FirebaseMessaging.token(): Result<String?> =
    suspendCancellableCoroutine { cont ->
        runCatching {
            FirebaseMessaging.getInstance().token.continueWith<String?> { null }
                .addOnCompleteListener {
                    it.result?.let {
                        cont.resume(Result.success(it))
                    }
                }.addOnFailureListener {
                    Log.e("Login", "Failed to login", it)
                    cont.resume(Result.success(null))
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