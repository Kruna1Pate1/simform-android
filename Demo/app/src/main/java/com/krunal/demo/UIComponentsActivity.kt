package com.krunal.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.krunal.demo.uicomponents.SliderFragment
import com.krunal.demo.uicomponents.SpanFragment
import com.krunal.demo.uicomponents.SpinnerFragment
import com.krunal.demo.uicomponents.binding.DataBindingFragment
import com.krunal.demo.uicomponents.cardscreen.CardFragment

class UIComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, CardFragment())
            .commit()
    }
}