package com.krunal.demo

import android.util.Log
import android.widget.TextView

fun show(msg: String, tag: String, v: TextView? = null) {
    v?.text = msg
    Log.d(tag, msg)
}