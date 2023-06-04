package com.krunal.demo.uicomponents.models

import android.graphics.Bitmap as AndroidBitmap

sealed interface DrawableResource {
    data class Drawable(val id: Int): DrawableResource
    data class Bitmap(val bitmap: AndroidBitmap): DrawableResource
}