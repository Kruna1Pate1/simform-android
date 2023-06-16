package com.krunal.demo.uicomponents.extentions

import android.content.res.TypedArray

fun TypedArray.toFloat(): List<Float> = buildList {
    for (i in 0 until length()) {
        add(getFloat(i, -1f))
    }
}

fun TypedArray.toInt(): List<Int> = buildList {
    for (i in 0 until length()) {
        add(getInt(i, -1))
    }
}