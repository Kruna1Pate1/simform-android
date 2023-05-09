package com.krunal.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krunal.demo.uicomponents.AppBarFragment
import com.krunal.demo.uicomponents.ButtonFragment
import com.krunal.demo.uicomponents.CheckboxFragment
import com.krunal.demo.uicomponents.ChipFragment
import com.krunal.demo.uicomponents.CustomViewFragment
import com.krunal.demo.uicomponents.EditTextFragment
import com.krunal.demo.uicomponents.FabFragment
import com.krunal.demo.uicomponents.LinearLayoutFragment
import com.krunal.demo.uicomponents.RadioFragment
import com.krunal.demo.uicomponents.SnackBarFragment
import com.krunal.demo.uicomponents.TabLayoutFragment
import com.krunal.demo.uicomponents.ToastFragment
import com.krunal.demo.uicomponents.constraintLayouts.CircularFragment

class UIComponentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, CircularFragment())
            .commit()
    }
}