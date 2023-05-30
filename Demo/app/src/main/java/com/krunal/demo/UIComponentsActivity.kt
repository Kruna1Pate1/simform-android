package com.krunal.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.krunal.demo.recyclerview.CalculateFragment
import com.krunal.demo.recyclerview.ChatDetailFragment
import com.krunal.demo.recyclerview.ChattingFragment
import com.krunal.demo.recyclerview.ContactDetailFragment
import com.krunal.demo.recyclerview.YoutubeFragment
import com.krunal.demo.recyclerview.YoutubeHomeFragment
import com.krunal.demo.uicomponents.ButtonFragment
import com.krunal.demo.uicomponents.CoordinatorLayoutFragment
import com.krunal.demo.uicomponents.ListViewFragment
import com.krunal.demo.uicomponents.ThemeFragment
import com.krunal.demo.uicomponents.cardscreen.CardFragment
import com.krunal.demo.uicomponents.extentions.isDarkMode
import com.krunal.demo.uicomponents.helpers.ThemeHelper
import com.krunal.demo.uicomponents.models.enums.AccentColor

class UIComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setupTheme()
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        setTheme(ThemeHelper.getThemeResource(ThemeHelper.getThemeAccent()))
        setTheme(ThemeHelper.getThemeResource(AccentColor.BLUE))
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, CalculateFragment())
            .commit()
    }
}