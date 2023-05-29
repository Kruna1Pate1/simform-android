package com.krunal.demo.stackexchange.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HexagonFloatingActionButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): FloatingActionButton(context, attrs, defStyleAttr) {

    private val hexagonPath = Path()

    override fun onDraw(canvas: Canvas?) {
//        clipHexagonShape(canvas)
        super.onDraw(canvas)
    }

    private fun clipHexagonShape(canvas: Canvas?) {
        hexagonPath.apply {
            moveTo(size / 2f, 0f)
            lineTo(size.toFloat(), height / 3f)
            lineTo(size.toFloat(), height * 2 / 3f)
            lineTo(size / 2f, height.toFloat())
            lineTo(0f, height * 2 / 3f)
            lineTo(0f, height / 3f)
            close()
        }
        canvas?.clipPath(hexagonPath)
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setConvexPath(hexagonPath)
            }
        }
    }
}