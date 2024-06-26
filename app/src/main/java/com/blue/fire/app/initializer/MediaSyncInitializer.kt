package com.blue.fire.app.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManagerInitializer
import com.blue.fire.app.client.MediaSyncClient

class MediaSyncInitializer : Initializer<MediaSyncClient> {
    override fun create(context: Context): MediaSyncClient {
        MediaSyncClient.init(context)
        return MediaSyncClient
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(WorkManagerInitializer::class.java, LoggerInitializer::class.java)
    }
}