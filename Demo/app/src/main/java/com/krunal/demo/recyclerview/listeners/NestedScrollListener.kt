package com.krunal.demo.recyclerview.listeners

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.recyclerview.extentions.canScrollLeft
import com.krunal.demo.recyclerview.extentions.canScrollRight

class NestedScrollListener : RecyclerView.SimpleOnItemTouchListener() {

    var startX = 0f

    override fun onInterceptTouchEvent(
        recyclerView: RecyclerView, event: MotionEvent
    ): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
            }

            MotionEvent.ACTION_MOVE -> {
                val isScrollingRight = event.x < startX
                val scrollItemsToRight = isScrollingRight && recyclerView.canScrollRight
                val scrollItemsToLeft = !isScrollingRight && recyclerView.canScrollLeft
                val disallowIntercept = scrollItemsToRight || scrollItemsToLeft
                recyclerView.parent.requestDisallowInterceptTouchEvent(
                    disallowIntercept
                )
            }

            MotionEvent.ACTION_UP -> {
                startX = 0f
            }

            else -> Unit
        }
        return false
    }
}