package com.krunal.demo.searchwebview.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivitySearchWebBinding
import com.krunal.demo.helpers.ThemeHelper
import com.krunal.demo.searchwebview.ui.fragments.WebViewFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchWebActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivitySearchWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchWebBinding.inflate(layoutInflater)
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
                    Log.d(
                        "Navigation",
                        "current: ${navController.currentBackStackEntry?.destination?.displayName}"
                    )
                }
            }
        }
    }


    /**
     * Forward [KeyEvent.KEYCODE_BACK] event to [WebViewFragment]
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            supportFragmentManager.fragments.firstOrNull()?.childFragmentManager?.let { navHostFragment ->
                (navHostFragment.fragments.firstOrNull() as? WebViewFragment)?.let { webViewFragment ->
                    if (webViewFragment.onBackPressed()) return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}