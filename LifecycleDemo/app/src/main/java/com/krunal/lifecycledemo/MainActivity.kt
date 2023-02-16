package com.krunal.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLabel = findViewById(R.id.tvLabel)
        show("onCreate", TAG, tvLabel)
        val fragment = DemoFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onStart() {
        super.onStart()
        show("onStart", TAG, tvLabel)
    }

    override fun onResume() {
        super.onResume()
        show("onResume", TAG, tvLabel)
    }

    override fun onPause() {
        super.onPause()
        show("onPause", TAG, tvLabel)
    }

    override fun onStop() {
        super.onStop()
        show("onStop", TAG, tvLabel)
    }

    override fun onRestart() {
        super.onRestart()
        show("onRestart", TAG, tvLabel)
    }

    override fun onDestroy() {
        super.onDestroy()
        show("onDestroy", TAG, tvLabel)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}