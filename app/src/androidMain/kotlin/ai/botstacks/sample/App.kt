/*
 * Copyright (c) 2023.
 */

package ai.botstacks.sample

import ai.botstacks.sdk.BotStacksChat
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        BotStacksChat.shared.setup(
            context = this,
            apiKey = getString(R.string.botstacks_api_key),
            giphyApiKey = getString(R.string.giphy_api_key),
        )
    }
}