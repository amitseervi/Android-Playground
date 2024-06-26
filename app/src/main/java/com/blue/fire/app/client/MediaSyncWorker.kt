package com.blue.fire.app.client

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import timber.log.Timber

class MediaSyncWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        Timber.tag("amittest").i("Sync Media")
        return Result.success()
    }
}