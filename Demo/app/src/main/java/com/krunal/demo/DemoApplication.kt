package com.krunal.demo

import android.app.Application
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.searchwebview.data.repositories.PackagesRepository
import com.krunal.demo.searchwebview.utils.PackageHelper

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        /**
         * Initialize [PreferenceHelper]
         */
        PreferenceHelper.initialize(applicationContext)

        /**
         * Initialize [PackageHelper]
         */
        PackageHelper.initialize(applicationContext)
    }

    companion object {
        lateinit var instance: Application
    }
}