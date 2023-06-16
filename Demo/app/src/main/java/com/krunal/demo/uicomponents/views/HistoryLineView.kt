package com.krunal.demo.uicomponents.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.krunal.demo.R

class HistoryLineView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var thumbColor = Color.BLUE
    private var lineColor = thumbColor
    private var thumbRadius = 10F
    private var lineWidth = 2F

    init {

        setupAttributes(attrs)
        setupPaint()
    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.HistoryLineView, 0, 0)
        thumbColor = typedArray.getColor(R.styleable.HistoryLineView_thumbColor, thumbColor)
        lineColor = typedArray.getColor(R.styleable.HistoryLineView_lineColor, lineColor)
        thumbRadius = typedArray.getDimension(R.styleable.HistoryLineView_thumbRadius, thumbRadius)
        lineWidth = typedArray.getDimension(R.styleable.HistoryLineView_lineWidth, lineWidth)

//        typedArray.recycle()
    }

    private fun setupPaint() {
        thumbPaint.apply {
            color = thumbColor
            style = Paint.Style.FILL
        }

        linePaint.apply {
            color = lineColor
            strokeWidth = lineWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawThumb(canvas)
        drawLine(canvas)
    }

    private fun drawThumb(canvas: Canvas) {
        canvas.drawCircle((width / 2).toFloat(), thumbRadius, thumbRadius, thumbPaint)
    }

    private fun drawLine(canvas: Canvas) {
        val xPosition = (width / 2).toFloat()
        val yStartPosition = (thumbRadius * 2) + lineWidth
        val yEndPosition = height - yStartPosition
        canvas.drawLine(
            xPosition, yStartPosition, xPosition, yEndPosition, linePaint
        )
    }
}