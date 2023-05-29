package com.krunal.demo.appcomponents.utils

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Observe and log [Lifecycle.Event] for [LifecycleOwner]
 */
class LifecycleLogger(lifecycleOwner: LifecycleOwner) {

    init {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                log(source::class.simpleName, event.name)
            }
        })
    }

    private fun log(name: String?, state: String) {
        Log.i(TAG, "[$name state]: $state")
    }

    companion object {
        private const val TAG = "LifecycleLogger"
    }
}