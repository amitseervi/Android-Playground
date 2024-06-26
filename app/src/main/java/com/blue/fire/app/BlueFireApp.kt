package com.blue.fire.app

import android.app.Application
import timber.log.Timber

class BlueFireApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}