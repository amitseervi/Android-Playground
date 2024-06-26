package com.blue.fire.app.client

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import timber.log.Timber

object MediaSyncClient {
    fun init(context: Context?) {
        context ?: return
        val workManager = WorkManager.getInstance(context)
        val workRequest = OneTimeWorkRequestBuilder<MediaSyncWorker>()
        workRequest.setConstraints(
            Constraints.Builder()
                .addContentUriTrigger(MediaStore.Images.Media.INTERNAL_CONTENT_URI, true)
                .addContentUriTrigger(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true)
                .build()
        )
        workManager.enqueue(workRequest.build())
    }
}