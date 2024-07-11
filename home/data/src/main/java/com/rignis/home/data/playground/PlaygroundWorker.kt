package com.rignis.home.data.playground

import android.content.Context
import android.content.pm.ServiceInfo
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.rignis.home.data.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val NOTIFICATION_CHANNEL_ID = "notification_channel"
private const val NOTIFICATION_CHANNEL_NAME = "playground"
private const val NOTIIFICATION_ID = 10101

class PlaygroundWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        createNotificationChannel(applicationContext)
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Playground notification")
            .setSmallIcon(R.drawable.ic_notification)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setProgress(100, 0, false)

        setForegroundAsync(
            ForegroundInfo(
                NOTIIFICATION_ID,
                notification.build(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        )
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        repeat(100) { index ->
            delay(1000)
            setProgress(workDataOf("progress" to (index + 1)))
            notificationManager.notify(
                NOTIIFICATION_ID,
                notification.setProgress(100, index + 1, false).build()
            )
        }
        return Result.success()
    }

    private suspend fun createNotificationChannel(context: Context) =
        withContext(Dispatchers.Main) {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) != null) {
                return@withContext
            }
            val notificationChannel = NotificationChannelCompat.Builder(
                NOTIFICATION_CHANNEL_ID,
                NotificationManagerCompat.IMPORTANCE_HIGH
            )
            notificationChannel.setName(NOTIFICATION_CHANNEL_NAME)
            notificationManager.createNotificationChannel(notificationChannel.build())
        }

}