package com.krunal.demo.uicomponents.extentions

import android.app.Activity
import android.util.TypedValue


fun Activity.getThemeColor(resId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}