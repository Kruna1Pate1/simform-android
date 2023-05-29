package com.krunal.demo.navigation.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityTriviaGameBinding
import com.krunal.demo.navigation.ui.viewmodels.TriviaGameViewModel
import com.krunal.demo.helpers.ThemeHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TriviaGameActivity : AppCompatActivity() {

    private lateinit var navController: NavController
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
            navController = it.navController
        }
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.toolbar.setupWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                navController.currentBackStackEntryFlow.collectLatest {
                    Log.d("Navigation", "current: ${navController.currentBackStackEntry?.destination?.displayName}")
                }
            }
        }
    }
}