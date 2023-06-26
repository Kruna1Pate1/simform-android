package com.krunal.demo.githubclient.ui.activity

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityGithubClientBinding
import com.krunal.demo.githubclient.ui.base.BaseActivity
import com.krunal.demo.githubclient.ui.viewmodel.GitHubClientViewModel
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubClientActivity : BaseActivity<ActivityGithubClientBinding, GitHubClientViewModel>() {

    private lateinit var navController: NavController

    override val viewModel: GitHubClientViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_github_client

    override fun onClick(v: View?) {
        // Implement when needed
    }

    override fun initialize() {
        super.initialize()
        checkAuthorizationStatus()
        setupUI()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        lifecycleScope.launch {
            viewModel.subtitle.collectLatest { subtitle ->
                binding.toolbar.subtitle = subtitle
            }
        }
    }

    fun updateSubtitle(subtitle: String?, owner: LifecycleOwner) {
        val lifecycle = owner.lifecycle
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.upTo(Lifecycle.State.RESUMED)) {
                    binding.toolbar.subtitle = subtitle
                } else {
                    binding.toolbar.subtitle = ""
                }
            }
        })
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        (supportFragmentManager.findFragmentById(R.id.gitHubHostFragmentContainer) as? NavHostFragment)?.let {
            navController = it.navController
        }
        setupNavigation()
    }

    // Setup and sync bottom navigation and app bar with fragment.
    private fun setupNavigation() {

        binding.toolbar.setupWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun checkAuthorizationStatus() {
        PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "").let { token ->
            if (token.isEmpty()) {
                val intent = Intent(this, AuthorizationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                viewModel.setToken(token)
            }
        }
    }
}