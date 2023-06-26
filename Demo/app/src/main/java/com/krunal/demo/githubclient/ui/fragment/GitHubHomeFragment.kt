package com.krunal.demo.githubclient.ui.fragment

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
import com.krunal.demo.databinding.FragmentGithubHomeBinding
import com.krunal.demo.githubclient.ui.adapter.HomeAdapter
import com.krunal.demo.githubclient.ui.adapter.ProfileAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.GitHubHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubHomeFragment : BaseFragment<FragmentGithubHomeBinding, GitHubHomeViewModel>() {

    private lateinit var homeAdapter: HomeAdapter
    override val viewModel: GitHubHomeViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_github_home

    override fun initialize() {
        super.initialize()
        setupHome()
        viewModel.getItems()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.homeItem.collectLatest { item ->
                    homeAdapter.submitList(item)
                }
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.menu_toolbar_github_home, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val direction = GitHubHomeFragmentDirections.actionGitHubHomeFragmentToChooseRepositoryFragment()
        findNavController().navigate(direction)
        return true
    }

    private fun setupHome() {
        homeAdapter = HomeAdapter()
        binding.rvHome.apply {
            adapter = homeAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }
}