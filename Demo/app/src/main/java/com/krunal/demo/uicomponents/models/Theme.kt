package com.krunal.demo.uicomponents.models

import androidx.annotation.ColorRes
import com.krunal.demo.uicomponents.models.enums.AccentColor

data class Theme(
    val name: String,
    val accentColor: AccentColor,
    val color: Int,
    val isDarkMode: Boolean = false
)
