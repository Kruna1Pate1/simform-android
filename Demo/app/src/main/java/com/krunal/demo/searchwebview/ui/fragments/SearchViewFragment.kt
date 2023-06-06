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
import com.krunal.demo.databinding.FragmentSearchViewBinding
import com.krunal.demo.databinding.FragmentUserProfileBinding
import com.krunal.demo.databinding.FragmentWebViewBinding
import com.krunal.demo.navigation.ui.fragments.UserProfileFragmentArgs
import com.krunal.demo.navigation.ui.viewmodels.UserProfileViewModel
import com.krunal.demo.searchwebview.ui.viewmodels.WebViewViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchViewBinding
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchViewBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewModel.loadUrl("https://google.com")

//        binding.webView.apply {
//            settings.javaScriptEnabled = true
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.url.collectLatest { url ->
//                    binding.webView.loadUrl(url)
                }
            }
        }
    }
}