package com.krunal.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.krunal.demo.recyclerview.fragments.ContactDetailFragment
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
            .replace(R.id.uiComponentsFragment, ContactDetailFragment())
            .commit()
    }
}