package com.krunal.lifecycledemo

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        show("onCreate", TAG)
    }

    override fun onTerminate() {
        super.onTerminate()
        show("onTerminate", TAG)
    }

    companion object {
        private const val TAG = "MyApplication"
    }
}