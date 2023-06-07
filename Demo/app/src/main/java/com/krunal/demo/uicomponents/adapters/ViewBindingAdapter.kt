package com.krunal.demo.uicomponents.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.ColorUtils
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter

@BindingAdapter("drawableResource")
fun ImageView.setDrawableResource(@DrawableRes drawableResource: Int) {
    setImageResource(drawableResource)
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

@BindingAdapter("textColorResource")
fun TextView.setTextColorResource(@ColorRes colorResource: Int) {
    setTextColor(resources.getColor(colorResource, context.theme))
}

@BindingAdapter("textColor")
fun TextView.setTextColor(color: Int) {
    setTextColor(color)
}

@BindingAdapter("backgroundColor")
fun View.setViewBackgroundColor(color: Int) {
    setBackgroundColor(color)
}

@BindingAdapter("gradientStartColor", "gradientEndColor", "angle", requireAll = false)
fun View.setGradientBackground(gradientStartColor: Int?, gradientEndColor: Int?, angle: Int?) {
    gradientStartColor?.let { it ->
        ColorUtils.setAlphaComponent(it, 0x4f).let { color ->
            GradientDrawable(
                GradientDrawable.Orientation.TR_BL,
                intArrayOf(color, gradientEndColor ?: Color.TRANSPARENT)
            ).also {
                it.cornerRadius = 0f
                background = it
            }
        }
    }
}

@BindingAdapter("android:layout_marginStart")
fun View.setStartMargin(margin: Float) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginStart = margin.toInt()
    }
}

@BindingAdapter("layout_width")
fun View.setWidth(width: Float) {
    updateLayoutParams {
        this.width = width.toInt()
    }
}

@BindingAdapter("layout_height")
fun View.setHeight(height: Float) {
    updateLayoutParams {
        this.height = height.toInt()
    }
}