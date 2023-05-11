package com.krunal.demo.uicomponents.extentions

import android.text.Spanned
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.toSpanned

// Add text to the LinearLayout by creating new TextView
fun LinearLayout.addTextView(text: String) {
    addTextView(text.toSpanned())
}

// Add spanned text to the LinearLayout by creating new TextView
fun LinearLayout.addTextView(spanText: Spanned) {
    TextView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply {
            topMargin = 24
        }
        text = spanText
        textSize = 18f
    }.also { textView ->
        addView(textView)
    }
}