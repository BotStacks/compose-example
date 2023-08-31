# InAppChat Android

![Maven Central](https://img.shields.io/maven-central/v/io.inappchat/sdk) ![GitHub issues](https://img.shields.io/github/issues/inappchat/android-example) 

Delightful chat for your Android apps

## Overview

This SDK integrates a fully serviced chat experience on the [InAppChat](https://inappchat.io) platform.

## Installation

Add `jitpack.io` to your repositories in your `settings.gradle`

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```

Add `io.inappchat:sdk:1.0.0` to your dependencies

```gradle

dependencies {
    implementation 'io.inappchat:sdk:1.0.0'
}

```

## Usage

The minimum it takes to add InAppChat is four lines of code. This repo is also an example app using the Android SDK. Feel free to clone it and view the source.

### Initialize the SDK

In your Application class, call InAppChat.setup with your API key. Create an account at [InAppChat.io](https://inappchat.io) to get your own API key. If you don't have an Application class, [create one](https://guides.codepath.com/android/Understanding-the-Android-Application-Class).

```kotlin
InAppChat.setup(apiKey)
```

### Render the UI

The InAppChat UI Kit uses Jetpack Compose.
You can add it to any `NavHost` by rendering inside an `InAppChatContext` and adding the `InAppChatRoutes`. Navigate to InAppChat by calling `navController.navigate("chats")`

```kotlin
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
```

## Push Notifications (Firebase Cloud Messaging)

For push notifications via FCM, just pass your push token to InAppChat

```kotlin
InAppChat.registerFCMToken(it)
```

## Giphy Support

If you'd like Giphy support in your chats, add the Giphy UI SDK to the your application.

```kotlin
Giphy.configure(this, getString(R.string.giphy))
```

## Theming

You can theme your InAppChat UI kit by passing in a theme to `InAppChatContext`. The theme supports fonts, colors and things like bubble border radius and image sizes. Provide a `Theme` to InAppChatUI

```kotlin
val customTheme = remember {
    makeTheme(this)
}
InAppChatContext(theme) {
    // App Content
}
```

### Colors, Fonts and Assets

You can provide your own color themes to the theme object with a wide array of parameters. The UI kit uses both a light and a dark theme so provide both, as well as Fonts and Assets. Here's an example makeTheme function that customizes fonts, colors and assets used by the SDK

```kotlin
fun makeTheme(context: Context): Theme {
    val primary = Color(0xFF92C748)
    val fontName = GoogleFont("Nunito")

    val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = array.com_google_android_gms_fonts_certs
    )
    val fontFamily = FontFamily(
            Font(googleFont = fontName, fontProvider = provider)
    )
    val app = { size: TextUnit, weight: FontWeight ->
        FontStyle(size, weight, fontFamily)
    }

    return Theme().apply {
        light = Colors(
                true,
                primary = primary,
                senderBubble = Color(0xFFC0D99E),
                senderUsername = primary
        )
        dark = Colors(
                false,
                primary = primary,
                senderBubble = Color(0xFFC0D99E),
                senderText = Color(0xFF202127),
                senderUsername = primary
        )
        fonts = Fonts(
                title = app(22.sp, FontWeight.ExtraBold),
                title2 = app(20.sp, FontWeight.SemiBold),
                title3 = app(15.sp, FontWeight.SemiBold),
                headline = app(13.sp, FontWeight.Bold),
                body = app(14.sp, FontWeight.Normal),
                caption = app(12.sp, FontWeight.Normal)
        )
        assets = Assets(
                drawable.pp_icon,
                emptyChannels = EmptyScreenConfig(
                        drawable.empty_channels,
                        "You haven't joined any channels yet"
                ),
                emptyChat = EmptyScreenConfig(
                        drawable.empty_chat,
                        "Your friends are ***dying*** to see you"
                ),
                emptyChats = EmptyScreenConfig(
                        drawable.empty_threads,
                        "You haven't added to any chats yet"
                ),
                emptyAllChannels = EmptyScreenConfig(
                        drawable.empty_all_channels,
                        "It's dead in here"
                )

        )
    }
}
```

Happy chatting!

All content copyright Rip Bull Networks, Inc
