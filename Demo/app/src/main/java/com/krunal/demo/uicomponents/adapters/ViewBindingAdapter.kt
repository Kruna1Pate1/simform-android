package com.krunal.demo.uicomponents.adapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.krunal.demo.uicomponents.models.DrawableResource

@BindingAdapter("drawableResource")
fun ImageView.setDrawableResource(drawableResource: DrawableResource) {
    when (drawableResource) {
        is DrawableResource.Bitmap -> setImageBitmap(drawableResource.bitmap)
        is DrawableResource.Drawable -> setImageResource(drawableResource.id)
    }
}

@BindingAdapter("drawableResource")
fun TextView.setStartDrawableResource(@DrawableRes drawableResource: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        AppCompatResources.getDrawable(context, drawableResource), null, null, null
    )
}

@BindingAdapter("drawableColorResource")
fun TextView.setStartColorResource(@ColorRes colorResource: Int) {
    compoundDrawablesRelative.filterNotNull().forEach { drawable ->
        drawable.mutate().setTint(resources.getColor(colorResource, context.theme))
    }
}

@BindingAdapter("backgroundColor")
fun View.setViewBackgroundColor(color: Int) {
    setBackgroundColor(color)
}