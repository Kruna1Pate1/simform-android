package com.krunal.demo

import android.app.Application
import com.krunal.demo.uicomponents.helpers.PreferenceHelper

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        /**
         * Initialize [PreferenceHelper]
         */
        PreferenceHelper.initialize(applicationContext)
    }

    companion object {
        lateinit var instance: Application
    }
}