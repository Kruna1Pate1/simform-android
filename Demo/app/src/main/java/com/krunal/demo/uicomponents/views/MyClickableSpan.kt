package com.krunal.demo.uicomponents.views

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class MyClickableSpan(
    private val onTextClick: () -> Unit
) : ClickableSpan() {
    override fun onClick(view: View) {
        onTextClick()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}