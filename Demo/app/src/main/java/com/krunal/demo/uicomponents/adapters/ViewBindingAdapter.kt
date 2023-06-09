package com.krunal.demo.uicomponents.adapters

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.ThumbnailUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

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

@BindingAdapter("imageUrl")
fun ImageView.bindImage(url: String?) {
    if (url == null) return

    findViewTreeLifecycleOwner()?.lifecycleScope?.launch(Dispatchers.IO) {
        runCatching {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            val bitmap = BitmapFactory.decodeStream(urlConnection.inputStream)
            setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap, 200, 200))
        }
    }
}