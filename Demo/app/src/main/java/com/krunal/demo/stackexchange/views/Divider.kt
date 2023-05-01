package com.krunal.demo.stackexchange.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.krunal.demo.R
import com.krunal.demo.uicomponents.extentions.getThemeColor

class Divider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.dividerStyle,
) : View(context, attrs, defStyleAttr) {

    private val progressPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var lineHeight = 5F
    private var progressColor =
        Color.RED
    private var backgroundColor =
        context.getThemeColor(com.google.android.material.R.attr.colorSurfaceDim)
    private var progress = 6F
    private var centerPositionY: Float = 5F


    init {
        setupAttributes(attrs, defStyleAttr)
        setupPaint()
    }

    private fun setupAttributes(attrs: AttributeSet?, defStyle: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.Divider, defStyle, 0).apply {
            backgroundColor = getColor(R.styleable.Divider_backgroundColor, backgroundColor)
            progressColor = getColor(R.styleable.Divider_progressColor, progressColor)
            progress = getDimension(R.styleable.Divider_progress, progress)
            lineHeight = getDimension(R.styleable.Divider_lineHeight, lineHeight)
            recycle()
        }
    }

    private fun setupPaint() {
        progressPaint.apply {
            color = progressColor
            strokeWidth = lineHeight
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }

        backgroundPaint.apply {
            color = backgroundColor
            strokeWidth = lineHeight
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        centerPositionY = height / 2F
        drawBackground(canvas)
        drawProgress(canvas)

        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, lineHeight.toInt())
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawLine(0f, centerPositionY, width.toFloat(), centerPositionY, backgroundPaint)
    }

    private fun drawProgress(canvas: Canvas) {
        // Draw left progress
        canvas.drawLine(0f, centerPositionY, progress, centerPositionY, progressPaint)

        // Draw right progress
        canvas.drawLine(width - progress, centerPositionY, width.toFloat(), centerPositionY, progressPaint)
    }
}