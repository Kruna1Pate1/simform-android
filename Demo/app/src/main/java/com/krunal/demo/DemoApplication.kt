package com.krunal.demo

import android.app.Application
import com.krunal.demo.uicomponents.helpers.PreferenceHelper

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Initialize [PreferenceHelper]
         */
        PreferenceHelper.initialize(applicationContext)
    }
}