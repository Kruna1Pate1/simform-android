package com.krunal.demo.recyclerview.decorations

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.textview.MaterialTextView
import com.krunal.demo.R
import com.krunal.demo.recyclerview.models.MessageType

class ChatItemDecoration : ItemDecoration() {

    var bottomSpacing = 40

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var colorSender = Color.GREEN
    var colorReceiver = Color.CYAN

    init {
        setupPaint()
    }

    private fun setupPaint() {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.FILL
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEach { view ->
            val viewRect = Rect()
            parent.getDecoratedBoundsWithMargins(view, viewRect)

            val txtView = view.findViewById<MaterialTextView>(R.id.tvMessage)
            val position = parent.getChildAdapterPosition(view)

            if (position == -1) return@forEach

            val viewType = parent.adapter?.getItemViewType(position) ?: return@forEach

            val rectF = RectF(
                viewRect.left + txtView.left.toFloat() - DECORATION_PADDING,
                viewRect.top.toFloat(),
                viewRect.left + txtView.right.toFloat() + DECORATION_PADDING,
                viewRect.bottom - bottomSpacing.toFloat()
            )

            val path = when (MessageType.values()[viewType]) {
                MessageType.SEND -> {
                    paint.color = colorSender
                    Path().apply {
                        moveTo(rectF.right + 40F, rectF.bottom)
                        lineTo(rectF.left, rectF.bottom)
                        lineTo(rectF.left, rectF.top)
                        lineTo(rectF.right, rectF.top)
                        quadTo(rectF.right, (rectF.top + rectF.bottom) / 2, rectF.right + 40F, rectF.bottom)
                        close()
                    }
                }

                MessageType.RECEIVE -> {
                    paint.color = colorReceiver
                    Path().apply {
                        moveTo(rectF.left - CURVE_LENGTH, rectF.bottom)
                        lineTo(rectF.right, rectF.bottom)
                        lineTo(rectF.right, rectF.top)
                        lineTo(rectF.left, rectF.top)
                        quadTo(rectF.left, (rectF.top + rectF.bottom) / 2, rectF.left - 20F, rectF.bottom)
                        close()
                    }
                }
            }
            canvas.drawPath(path, paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.bottom += bottomSpacing
    }

    companion object {
        const val DECORATION_PADDING = 30F
        private const val CURVE_LENGTH = 30F
    }
}