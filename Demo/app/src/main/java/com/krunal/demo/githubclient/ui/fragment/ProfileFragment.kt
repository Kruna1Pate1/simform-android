package com.krunal.demo.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.view.iterator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.adapter.ProfileAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(), MenuProvider {

    private lateinit var profileAdapter: ProfileAdapter
    override val viewModel: ProfileViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_profile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_profile, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val direction = ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment()
        findNavController().navigate(direction)
        return true
    }

    private fun setupProfile() {
        profileAdapter = ProfileAdapter()
        binding.rvProfile.apply {
            adapter = profileAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}