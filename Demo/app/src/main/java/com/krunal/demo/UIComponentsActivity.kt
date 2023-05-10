package com.krunal.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krunal.demo.uicomponents.SpinnerFragment

class UIComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, SpinnerFragment())
            .commit()
    }
}