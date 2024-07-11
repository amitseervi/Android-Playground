package com.rignis.home.data.playground

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.rignis.home.domain.PlayGroundUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

class PlayGroundUsecaseImpl @Inject constructor(private val application: Application) :
    PlayGroundUsecase {
    private val _currentProgress: MutableStateFlow<Int> = MutableStateFlow(0)
    override val currentProgress: StateFlow<Int>
        get() = _currentProgress

    init {
        val workManager = WorkManager.getInstance(application)
        workManager.getWorkInfoByIdLiveData(UUID.fromString(WORK_ID)).observeForever { workInfo ->
            if (workInfo != null) {
                _currentProgress.value = workInfo.progress.getInt("progress", 0)
            }
        }
    }

    override fun playAround() {
        val workManager = WorkManager.getInstance(application)
        val workRequest = OneTimeWorkRequestBuilder<PlaygroundWorker>().setId(
            UUID.fromString(
                WORK_ID
            )
        ).build()
        workManager.enqueueUniqueWork("plaground", ExistingWorkPolicy.REPLACE, workRequest)
    }

    companion object {
        private const val WORK_ID = "12ef366f-86ee-4781-9c5d-76fcbb18e638"
    }
}