package com.krunal.demo.githubclient.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.adapter.ProfileAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var profileAdapter: ProfileAdapter
    override val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun getLayoutResId(): Int = R.layout.fragment_profile

    override fun initialize() {
        super.initialize()
        setupProfile()
        viewModel.getUser()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.profile.collectLatest { profile ->
                    profile?.items?.let(profileAdapter::submitList)
                }
            }
        }
    }

    private fun setupProfile() {
        profileAdapter = ProfileAdapter()
        binding.rvProfile.adapter = profileAdapter
    }
}