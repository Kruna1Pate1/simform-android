package com.krunal.demo.uicomponents.adapters

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.krunal.demo.uicomponents.models.DrawableResource

@BindingAdapter("drawableResource")
fun ImageView.setDrawableResource(drawableResource: DrawableResource) {
    when (drawableResource) {
        is DrawableResource.Bitmap -> setImageBitmap(drawableResource.bitmap)
        is DrawableResource.Drawable -> setImageResource(drawableResource.id)
    }
}

@BindingAdapter("backgroundColor")
fun View.setViewBackgroundColor(color: Int) {
    setBackgroundColor(color)
}