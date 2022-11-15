package com.example.appbharat

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppBharatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("SHAW_TAG", "onCreate: init ")
    }


}