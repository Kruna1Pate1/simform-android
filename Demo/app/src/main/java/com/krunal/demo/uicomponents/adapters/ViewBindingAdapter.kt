package com.krunal.demo.uicomponents.adapters

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.krunal.demo.uicomponents.models.DrawableResource

@BindingAdapter("drawableResource")
fun ImageView.setDrawableResource(@DrawableRes drawableResource: Int) {
    setImageResource(drawableResource)
}

@BindingAdapter("backgroundColor")
fun View.setViewBackgroundColor(color: Int) {
    setBackgroundColor(color)
}