package com.krunal.demo.navigation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityTriviaGameBinding
import com.krunal.demo.navigation.ui.viewmodels.TriviaGameViewModel
import com.krunal.demo.helpers.ThemeHelper

class TriviaGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTriviaGameBinding
    private val viewModel: TriviaGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriviaGameBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        installSplashScreen()
        setupTheme()
        setContentView(binding.root)
        setupUI()
    }

    private fun setupTheme() {
        setTheme(ThemeHelper.getThemeResource(ThemeHelper.getThemeAccent()))
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        (supportFragmentManager.findFragmentById(R.id.navHost) as? NavHostFragment)?.let {
            binding.toolbar.setupWithNavController(it.navController)
        }
    }
}