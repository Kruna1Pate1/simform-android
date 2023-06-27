package com.krunal.demo.githubclient.ui.activity

import android.content.Intent
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.krunal.demo.R
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.ActivityAuthorizationBinding
import com.krunal.demo.githubclient.ui.base.BaseActivity
import com.krunal.demo.githubclient.ui.viewmodel.AuthorizationViewModel
import com.krunal.demo.githubclient.util.GitHubUrls
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.utils.AppConstants.GITHUB_CLIENT_TAG
import com.krunal.demo.utils.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthorizationActivity : BaseActivity<ActivityAuthorizationBinding, AuthorizationViewModel>() {

    override val viewModel: AuthorizationViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_authorization

    override fun initialize() {
        super.initialize()
        checkAuthorizationStatus()
        setClickListener()
        setTermAndPrivacyText()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        lifecycleScope.launch {
            launch {
                viewModel.authorizationToken.collect { token ->
                    token?.let {
                        PreferenceHelper.putString(PreferenceKeys.AUTHORIZATION_TOKEN, it)
                        setTokenAndFinish(it)
                    }
                }
            }
            launch {
                viewModel.errorMessage.collectLatest { message ->
                    if (message.isNotEmpty()) {
                        Toast.makeText(this@AuthorizationActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignIn -> {
                openLink(GitHubUrls.AUTHORIZATION_URL)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.getQueryParameter(AppConstants.GITHUB_CODE)?.let { code ->
            Log.d(GITHUB_CLIENT_TAG, "code: $code")
            viewModel.generateAuthorizationToken(code)
        }
    }

    private fun checkAuthorizationStatus() {
        val token = PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "")
        if (token.isNotEmpty()) {
            setTokenAndFinish(token)
        }
    }

    private fun setClickListener() {
        binding.btnSignIn.setOnClickListener(this)
    }

    private fun setTermAndPrivacyText() {
        val termOfUse = getString(R.string.term_of_use)
        val privacyPolicy = getString(R.string.privacy_policy)
        val termAndPrivacy = getString(R.string.term_and_privacy, termOfUse, privacyPolicy)
        val termStartIndex = termAndPrivacy.indexOf(termOfUse)
        val termEndIndex = termStartIndex + termOfUse.length
        val privacyStartIndex = termAndPrivacy.indexOf(privacyPolicy)
        val privacyEndIndex = privacyStartIndex + privacyPolicy.length

        val termClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                openLink(GitHubUrls.TERM_OF_USE)
            }
        }
        val privacyClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                openLink(GitHubUrls.PRIVACY_POLICY)
            }
        }

        binding.txtTermAndPrivacyPolicy.apply {
            text = SpannableStringBuilder(termAndPrivacy).also { spanBuilder ->
                spanBuilder.setSpan(
                    termClickable, termStartIndex, termEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spanBuilder.setSpan(
                    privacyClickable,
                    privacyStartIndex,
                    privacyEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun openLink(url: String) {
        Intent(Intent.ACTION_VIEW, url.toUri()).also { intent ->
            startActivity(intent)
        }
    }

    private fun setTokenAndFinish(token: String) {
        val intent = Intent(this, GitHubClientActivity::class.java).apply {
            putExtra(IntentData.GITHUB_AUTHORIZATION_TOKEN, token)
        }
        startActivity(intent)
        finish()
    }
}