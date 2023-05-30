package com.krunal.demo.recyclerview.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceDecoration(val orientation: Int = VERTICAL) : ItemDecoration() {

    var spacing = 10

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (orientation == VERTICAL) {
            outRect.top = spacing
            outRect.bottom = spacing
        } else {
            outRect.left = spacing
            outRect.right = spacing
        }
    }

    companion object {
        const val VERTICAL = 0
        const val HORIZONTAL = 1
    }
}