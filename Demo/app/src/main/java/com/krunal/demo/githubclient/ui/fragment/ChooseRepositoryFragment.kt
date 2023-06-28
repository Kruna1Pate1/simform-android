package com.krunal.demo.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentChooseRepositoryBinding
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.listener.ItemClickListener
import com.krunal.demo.githubclient.ui.activity.GitHubClientActivity
import com.krunal.demo.githubclient.ui.adapter.RepoAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.ChooseRepositoryViewModel
import com.krunal.demo.githubclient.ui.viewmodel.GitHubClientViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChooseRepositoryFragment :
    BaseFragment<FragmentChooseRepositoryBinding, ChooseRepositoryViewModel>() {

    private lateinit var repoAdapter: RepoAdapter
    override val viewModel: ChooseRepositoryViewModel by viewModels()
    private val activityViewModel: GitHubClientViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_choose_repository

    override fun initialize() {
        super.initialize()
        activityViewModel.setSubtitle(getString(R.string.create_issue))
        setupRepositories()
        viewModel.getRepositories()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.repositories.collectLatest { repos ->
                    repoAdapter.submitList(repos)
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityViewModel.setSubtitle("")
    }

    private fun setupRepositories() {
        repoAdapter = RepoAdapter()
        binding.rvRepository.apply {
            adapter = repoAdapter
        }
        repoAdapter.itemClickListener = object : ItemClickListener<RepoCard> {
            override fun onClick(item: RepoCard, position: Int) {
                val direction = ChooseRepositoryFragmentDirections.actionChooseRepositoryFragmentToCreateIssueFragment(item.repoFullName)
                findNavController().navigate(direction)
            }
        }
    }
}