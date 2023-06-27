package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentRepositoryDetailBinding
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.data.local.RepoDetailItem
import com.krunal.demo.githubclient.listener.ItemClickListener
import com.krunal.demo.githubclient.ui.adapter.RepoAdapter
import com.krunal.demo.githubclient.ui.adapter.RepoDetailAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.RepositoriesViewModel
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

    override fun getLayoutResId(): Int = R.layout.fragment_repositories

    override fun initialize() {
        super.initialize()
        setupRepositories()
        viewModel.getRepository(args.repositoryName)
    }

    private fun setupRepositories() {
        repoDetailAdapter = RepoDetailAdapter()
        binding.rvRepository.apply {
            adapter = repoDetailAdapter
        }
        repoDetailAdapter.itemClickListener = object : ItemClickListener<RepoDetailItem> {
            override fun onClick(item: RepoDetailItem, position: Int) {
                if (item.title == getString(R.string.releases)) {
                    // TODO: viewModel.getReleases()
                }
            }
        }
    }
}