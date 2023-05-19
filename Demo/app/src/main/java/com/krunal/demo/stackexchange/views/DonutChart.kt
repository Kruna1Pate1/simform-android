package com.krunal.demo.stackexchange.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.krunal.demo.R
import com.krunal.demo.uicomponents.extentions.toFloat
import com.krunal.demo.uicomponents.extentions.toInt
import kotlin.math.min

class DonutChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private var colors: List<Int> =
        listOf(Color.YELLOW, Color.RED, Color.GREEN, Color.CYAN)//emptyList()
    private var values: List<Float> = listOf(25F, 30F, 10F, 35F)//emptyList()
    private var donutSpacing = 5F
    private val angles by lazy { values.map { it.toAngle() - donutSpacing } }
    private var lineWidth = 15F
    private var totalValue = 100
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private var currentAngle = 270f // Make initial angle top
    private var radius = -1F

    init {
        setupAttributes(attrs, defStyleAttr)
        setupPaint()
    }

    private fun setupAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.DonutChart, defStyleAttr, 0).apply {
            donutSpacing = getDimension(R.styleable.DonutChart_donutSpacing, donutSpacing)
            lineWidth = getDimension(R.styleable.DonutChart_strokeWidth, lineWidth)
            totalValue = getInt(R.styleable.DonutChart_totalValue, totalValue)
            radius = getDimension(R.styleable.DonutChart_radius, radius)

            if (hasValue(R.styleable.DonutChart_values)) {
                val valuesId = getResourceId(R.styleable.DonutChart_values, 0)
                resources.obtainTypedArray(valuesId).also { typedArray ->
                    values = typedArray.toFloat()
                    typedArray.recycle()
                }
            }

            if (hasValue(R.styleable.DonutChart_colors)) {
                val colorId = getResourceId(R.styleable.DonutChart_colors, 0)
                resources.obtainTypedArray(colorId).also { typedArray ->
                    colors = typedArray.toInt()
                    typedArray.recycle()
                }
            }
            recycle()
        }
    }

    private fun setupPaint() {
        linePaint.apply {
            strokeWidth = lineWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        radius = if (radius == DEFAULT_RADIUS) {
            val minSize = min(height, width)
            minSize / 2 - (minSize/10F)
        } else {
            radius
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        setupRectF()
        drawGraph(canvas)
        super.onDraw(canvas)
    }

    private fun setupRectF() {
        val centerX = width / 2F
        val centerY = height / 2F
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    private fun drawGraph(canvas: Canvas) {
        angles.forEachIndexed { index, value ->
            linePaint.color = colors[index]
            drawDonut(canvas, linePaint, currentAngle, value)
            currentAngle += value + donutSpacing
        }
    }

    private fun drawDonut(canvas: Canvas, paint: Paint, startAngle: Float, sweepAngle: Float) {
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)
    }

    private fun Float.toAngle(): Float = ((this * 360F) / totalValue)

    companion object {
        private const val TAG = "DonutChart"
        private const val DEFAULT_RADIUS = -1F
        private const val CHART_PADDING = 100F
    }
}