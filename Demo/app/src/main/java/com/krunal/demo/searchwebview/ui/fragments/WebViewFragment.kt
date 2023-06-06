package com.krunal.demo.searchwebview.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentUserProfileBinding
import com.krunal.demo.databinding.FragmentWebViewBinding
import com.krunal.demo.navigation.ui.fragments.UserProfileFragmentArgs
import com.krunal.demo.navigation.ui.viewmodels.UserProfileViewModel
import com.krunal.demo.searchwebview.ui.viewmodels.WebViewViewModel
import com.krunal.demo.searchwebview.utils.WebClient
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        viewModel.loadUrl("https://www.facebook.com")

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebClient()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.url.collectLatest { url ->
                    binding.webView.loadUrl(url)
                }
            }
        }
    }
}