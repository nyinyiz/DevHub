package com.nyinyi.devhub

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DevHubApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}