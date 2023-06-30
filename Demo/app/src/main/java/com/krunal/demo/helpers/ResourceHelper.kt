package com.krunal.demo.helpers

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.content.res.AppCompatResources

object ResourceHelper {

    lateinit var resources: Resources

    /**
     * set the context that is being used to access the shared preferences
     */
    fun initialize(context: Context) {
        resources = context.resources
    }
}