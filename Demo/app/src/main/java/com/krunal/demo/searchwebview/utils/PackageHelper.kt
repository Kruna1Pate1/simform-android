package com.krunal.demo.searchwebview.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.PackageManagerCompat

object PackageHelper {
    private lateinit var packageManager: PackageManager

    /**
     * set the context that is being used to access the shared preferences
     */
    fun initialize(context: Context) {

        packageManager = context.packageManager
    }

    fun getPackages() {
//        PackageManagerCompat
    }
}