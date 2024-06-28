/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blue.fire.app.client

import android.content.Context
import android.provider.MediaStore
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

object MediaSyncClient {
    fun init(context: Context?) {
        context ?: return
        val workManager = WorkManager.getInstance(context)
        val workRequest = OneTimeWorkRequestBuilder<MediaSyncWorker>()
        workRequest.setConstraints(
            Constraints
                .Builder()
                .addContentUriTrigger(MediaStore.Images.Media.INTERNAL_CONTENT_URI, true)
                .addContentUriTrigger(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true)
                .build(),
        )
        workManager.enqueue(workRequest.build())
    }
}