package com.krunal.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krunal.demo.uicomponents.ProgressBarFragment
import com.krunal.demo.uicomponents.SeekBarFragment
import com.krunal.demo.uicomponents.picker.DatePickerFragment
import com.krunal.demo.uicomponents.picker.TimePickerFragment

class UIComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicomponents)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.uiComponentsFragment, SeekBarFragment())
            .commit()
    }
}