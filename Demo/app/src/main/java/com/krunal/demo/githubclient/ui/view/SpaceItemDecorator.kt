package com.krunal.demo.githubclient.ui.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecorator(val spacing: Int = 10, val orientation: Int = VERTICAL) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (orientation == VERTICAL) {
            outRect.offset(0, spacing)
        } else {
            outRect.offset(spacing, 0)
        }
    }

    companion object {
        const val VERTICAL = 0
        const val HORIZONTAL = 1
    }
}