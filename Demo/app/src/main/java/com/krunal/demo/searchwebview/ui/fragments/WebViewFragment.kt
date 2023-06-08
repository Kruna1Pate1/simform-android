package com.krunal.demo.searchwebview.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.krunal.demo.databinding.FragmentWebViewBinding
import com.krunal.demo.searchwebview.helpers.WebClient
import com.krunal.demo.searchwebview.ui.viewmodels.WebViewViewModel
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
        binding.webView.apply {
            webViewClient = WebClient()
            settings.javaScriptEnabled = true
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.url.collectLatest { url ->
                    binding.webView.loadUrl(url)
                }
            }
        }
    }

    fun onBackPressed(): Boolean {
        binding.webView.apply {
            return if (canGoBack()) {
                goBack()
                true
            } else {
                false
            }
        }
    }
}