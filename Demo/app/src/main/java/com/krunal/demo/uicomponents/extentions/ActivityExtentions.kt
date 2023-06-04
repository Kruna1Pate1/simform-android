package com.krunal.demo.uicomponents.extentions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue


fun Context.getThemeColor(resId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}

val Activity.isDarkMode: Boolean
    get() = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_UNDEFINED -> false
        else -> false
    }