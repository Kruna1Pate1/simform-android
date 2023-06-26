package com.krunal.demo.githubclient.ui.fragment

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.activity.AuthorizationActivity
import com.krunal.demo.githubclient.ui.adapter.ProfileAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.dialog.LogoutConfirmationDialog
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var profileAdapter: ProfileAdapter
    override val viewModel: ProfileViewModel by viewModels()

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_profile, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.actionEdit -> {
                val direction =
                    ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment()
                findNavController().navigate(direction)
                true
            }

            R.id.actionLogout -> {
                logout()
                true
            }

            else -> false
        }
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

    private fun logout() {
        val dialog = LogoutConfirmationDialog {
            viewModel.logout {
                val intent = Intent(requireContext(), AuthorizationActivity::class.java)
                startActivity(intent)
            }
        }
        dialog.show(childFragmentManager, LogoutConfirmationDialog.TAG)
    }
}