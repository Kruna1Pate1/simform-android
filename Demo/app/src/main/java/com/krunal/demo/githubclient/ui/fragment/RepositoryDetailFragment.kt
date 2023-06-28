package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentRepositoryDetailBinding
import com.krunal.demo.githubclient.data.local.RepoDetailItem
import com.krunal.demo.githubclient.listener.ItemClickListener
import com.krunal.demo.githubclient.ui.adapter.RepoDetailAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.RepositoryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryDetailFragment :
    BaseFragment<FragmentRepositoryDetailBinding, RepositoryDetailViewModel>() {

    private lateinit var repoDetailAdapter: RepoDetailAdapter
    private val args: RepositoryDetailFragmentArgs by navArgs()
    override val viewModel: RepositoryDetailViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_repository_detail

    override fun initialize() {
        super.initialize()
        setupRepository()
        viewModel.getRepository(args.repositoryName)
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.repository.collectLatest { detail ->
                    detail?.repoItems?.let { list ->
                        repoDetailAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun setupRepository() {
        repoDetailAdapter = RepoDetailAdapter()
        binding.rvRepository.adapter = repoDetailAdapter
        repoDetailAdapter.itemClickListener = object : ItemClickListener<RepoDetailItem> {
            override fun onClick(item: RepoDetailItem, position: Int) {
                if (item.title == getString(R.string.releases)) {
                    viewModel.repository.value?.repoFullName?.let { repoName ->
                        val directions =
                            RepositoryDetailFragmentDirections.actionRepositoryDetailFragmentToReleaseFragment(
                                repoName
                            )
                        findNavController().navigate(directions)
                    }
                }
            }
        }
    }
}