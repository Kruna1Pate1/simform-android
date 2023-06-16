package com.krunal.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.krunal.demo.uicomponents.ButtonFragment
import com.krunal.demo.uicomponents.CoordinatorLayoutFragment
import com.krunal.demo.uicomponents.ThemeFragment
import com.krunal.demo.uicomponents.cardscreen.CardFragment
import com.krunal.demo.uicomponents.helpers.ThemeHelper

class UIComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setupTheme()
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupTheme() {
        setTheme(ThemeHelper.getThemeResource(ThemeHelper.getThemeAccent()))
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, CoordinatorLayoutFragment())
            .commit()
    }
}