package com.krunal.demo.helpers

import android.content.Context
import com.krunal.demo.R
import com.krunal.demo.uicomponents.models.Theme
import com.krunal.demo.uicomponents.models.enums.AccentColor
import com.krunal.demo.uicomponents.models.enums.ThemeMode
import com.krunal.demo.utils.PreferenceKeys

object ThemeHelper {
    fun getThemes(context: Context, isDark: Boolean = false): List<Theme> {
        val accentValues = AccentColor.values()
        val accent = context.resources.getStringArray(R.array.accents)

        return buildList {
            for (i in accentValues.indices) {
                add(
                    Theme(
                        accent[i], accentValues[i], getAccentColor(context, accentValues[i], isDark)
                    )
                )
            }
        }
    }

    fun getThemeResource(accentColor: AccentColor): Int {
        return when (accentColor) {
            AccentColor.RED -> R.style.Theme_Red
            AccentColor.BLUE -> R.style.Theme_Blue
            AccentColor.YELLOW -> R.style.Theme_Yellow
            AccentColor.GREEN -> R.style.Theme_Green
            AccentColor.PURPLE -> R.style.Theme_Purple
            AccentColor.VIOLET -> R.style.Theme_Violet
        }
    }

    fun getThemeMode(): ThemeMode {
        return ThemeMode.valueOf(
            PreferenceHelper.getString(
                PreferenceKeys.THEME_MODE, ThemeMode.AUTO.name
            )
        )
    }

    fun setThemeMode(mode: ThemeMode) {
        PreferenceHelper.putString(PreferenceKeys.THEME_MODE, mode.name)
    }

    fun getThemeAccent(): AccentColor {
        return AccentColor.valueOf(
            PreferenceHelper.getString(
                PreferenceKeys.ACCENT_COLOR, AccentColor.VIOLET.name
            )
        )
    }

    fun setThemeAccent(color: AccentColor) {
        PreferenceHelper.putString(PreferenceKeys.ACCENT_COLOR, color.name)
    }

    fun setDarkMode(isDark: Boolean) {
        PreferenceHelper.putBoolean(PreferenceKeys.DARK_MODE, isDark)
    }

    fun getDarkMode(): Boolean {
        return PreferenceHelper.getBoolean(PreferenceKeys.DARK_MODE, true)
    }

    private fun getAccentColor(
        context: Context, accentColor: AccentColor, isDarkMode: Boolean
    ): Int {
        return context.getColor(
            when (accentColor) {
                AccentColor.RED -> if (isDarkMode) R.color.red_md_theme_dark_primary else R.color.red_md_theme_light_primary
                AccentColor.BLUE -> if (isDarkMode) R.color.blue_md_theme_dark_primary else R.color.blue_md_theme_light_primary
                AccentColor.YELLOW -> if (isDarkMode) R.color.yellow_md_theme_dark_primary else R.color.yellow_md_theme_light_primary
                AccentColor.GREEN -> if (isDarkMode) R.color.green_md_theme_dark_primary else R.color.green_md_theme_light_primary
                AccentColor.PURPLE -> if (isDarkMode) R.color.purple_md_theme_dark_primary else R.color.purple_md_theme_light_primary
                AccentColor.VIOLET -> if (isDarkMode) R.color.violet_theme_dark_primary else R.color.violet_theme_light_primary
            }
        )
    }
}