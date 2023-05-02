package com.krunal.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krunal.demo.uicomponents.ButtonFragment
import com.krunal.demo.uicomponents.CheckboxFragment
import com.krunal.demo.uicomponents.CustomViewFragment
import com.krunal.demo.uicomponents.EditTextFragment
import com.krunal.demo.uicomponents.FabFragment
import com.krunal.demo.uicomponents.RadioFragment
import com.krunal.demo.uicomponents.SnackBarFragment
import com.krunal.demo.uicomponents.ToastFragment

class UIComponentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, SnackBarFragment())
            .commit()
    }
}