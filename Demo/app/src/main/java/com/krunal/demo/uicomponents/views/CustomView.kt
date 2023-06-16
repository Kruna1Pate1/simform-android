package com.krunal.demo.uicomponents.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.krunal.demo.R

class CustomView(context: Context, attrs: AttributeSet): View(context, attrs) {

    private val paint = Paint()
    private var centerOfX =  340F
    private var centerOfY =  340F
    private val radiusOfCircleView =  140F
    private var isCenter = false

    init {
        val attributeArray: TypedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomButton, 0, 0
        )

        paint.apply {
            color = attributeArray.getColor(R.styleable.CustomButton_circleColor, Color.RED)
            strokeWidth = attributeArray.getFloat(R.styleable.CustomButton_strokeSize, 20f)
            style = Paint.Style.STROKE
        }
        isCenter = attributeArray.getBoolean(R.styleable.CustomButton_onCenter, isCenter)
    }

    override fun onDraw(canvas: Canvas?) {
        if (isCenter) {
            centerOfX = (width / 2).toFloat()
            centerOfY = (height / 2).toFloat()
        }
        canvas?.drawCircle(centerOfX, centerOfY, radiusOfCircleView, paint)
        super.onDraw(canvas)
    }
}