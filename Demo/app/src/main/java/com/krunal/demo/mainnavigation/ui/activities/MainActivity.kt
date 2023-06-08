package com.krunal.demo.mainnavigation.ui.activities

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityMainBinding
import com.krunal.demo.helpers.ThemeHelper
import com.krunal.demo.mainnavigation.ui.fragments.HostFragment
import com.krunal.demo.mainnavigation.ui.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        installSplashScreen()
        setupTheme()
        setContentView(binding.root)
    }

    private fun setupTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setupDarkMode()
        }
        setTheme(ThemeHelper.getThemeResource(ThemeHelper.getThemeAccent()))
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupDarkMode() {
        (getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager)?.let { uiManager ->
            uiManager.setApplicationNightMode(
                if (ThemeHelper.getDarkMode()) {
                    UiModeManager.MODE_NIGHT_YES
                } else {
                    UiModeManager.MODE_NIGHT_NO
                }
            )
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments[0] !is HostFragment) {
            supportFragmentManager.commit {
                replace(R.id.hostFragmentContainer, HostFragment::class.java, null)
            }
            return
        }

        if (viewModel.isHomeComponents) {
            super.onBackPressed()
        } else {
            viewModel.homeComponents()
        }
    }
}