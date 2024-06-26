package com.blue.fire.app.client

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MediaSyncWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return Result.success()
    }
}