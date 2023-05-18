package com.krunal.demo.stackexchange.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DonutChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyle, defStyleRes) {

    private val colors: List<Int> = listOf(Color.RED, Color.YELLOW, Color.CYAN)
    private val values: List<Int> = listOf(25, 40, 35)
    private val angles by lazy { values.map { it.angle - innerPadding } }
    private val innerPadding = 5F
    private val lineWidth = 15F
    private val totalValue = 100
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private var currentAngle = 270f // Make initial angle top
    private val radius = 400F

    init {
        setupPaint()
    }

    private fun setupPaint() {
        linePaint.apply {
            strokeWidth = lineWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }

        rectF.set(lineWidth, lineWidth, radius * 2 - lineWidth, radius * 2 - lineWidth)
    }

    override fun onDraw(canvas: Canvas) {
        drawGraph(canvas)
        super.onDraw(canvas)
    }

    private fun drawGraph(canvas: Canvas) {
        angles.forEachIndexed { index, value ->
            linePaint.color = colors[index]
            drawDonut(canvas, linePaint, currentAngle, value)
            currentAngle += value + innerPadding
        }
    }

    private fun drawDonut(canvas: Canvas, paint: Paint, startAngle: Float, sweepAngle: Float) {
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)
    }

    private val Int.angle: Float
        get() = ((this * 360F) / totalValue)
}