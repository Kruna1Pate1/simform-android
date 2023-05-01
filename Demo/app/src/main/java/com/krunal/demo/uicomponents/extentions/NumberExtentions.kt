package com.krunal.demo.uicomponents.extentions

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun Int.dpFormat(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    return (this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}