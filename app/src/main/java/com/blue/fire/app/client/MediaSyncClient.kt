package com.blue.fire.app.client

import android.content.Context
import android.util.Log

object MediaSyncClient {
    fun init(context: Context?) {
        Log.i("amittest", "Media Sync Client initialized")

    }

    private var mInitialized: Boolean = false
}