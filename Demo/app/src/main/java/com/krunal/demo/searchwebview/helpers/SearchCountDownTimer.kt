package com.krunal.demo.searchwebview.helpers

import android.os.CountDownTimer

class SearchCountDownTimer(delay: Long, val onComplete: () -> Unit): CountDownTimer(delay, delay) {
    override fun onTick(millisUntilFinished: Long) {
    }

    override fun onFinish() {
        onComplete()
    }
}