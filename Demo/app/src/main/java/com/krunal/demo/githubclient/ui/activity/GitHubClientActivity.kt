package com.krunal.demo.githubclient.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityGitHubClientBinding
import com.krunal.demo.githubclient.ui.base.BaseActivity
import com.krunal.demo.githubclient.ui.viewmodel.GitHubClientViewModel
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubClientActivity : BaseActivity<ActivityGitHubClientBinding, GitHubClientViewModel>() {

    override val viewModel: GitHubClientViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_git_hub_client

    override fun onClick(v: View?) {
        // Implement when needed
    }

    override fun initialize() {
        super.initialize()
        checkAuthorizationStatus()
        viewModel.getUser()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        lifecycleScope.launch {
//            viewModel.user
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