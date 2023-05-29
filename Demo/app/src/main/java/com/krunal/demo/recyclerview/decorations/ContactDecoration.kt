package com.krunal.demo.recyclerview.decorations

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ContactDecoration(context: Context, orientation: Int): DividerItemDecoration(context, orientation) {

    var horizontalMargin = 16

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = horizontalMargin
        outRect.bottom = horizontalMargin
    }
}