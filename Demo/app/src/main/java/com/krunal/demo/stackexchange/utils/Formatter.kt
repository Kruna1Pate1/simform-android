package com.krunal.demo.stackexchange.utils

import java.text.NumberFormat
import java.util.Locale

object Formatter {
    private val formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY).apply {
        minimumFractionDigits = 0
    }

    @JvmStatic
    fun euroFormat(number: Float): String = formatter.format(number).removeSuffix("€")
}