package com.krunal.demo.githubclient.ui.fragment

import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentReleaseBinding
import com.krunal.demo.databinding.FragmentRepositoryDetailBinding
import com.krunal.demo.githubclient.data.local.ReleaseAsset
import com.krunal.demo.githubclient.data.local.RepoDetailItem
import com.krunal.demo.githubclient.listener.ItemClickListener
import com.krunal.demo.githubclient.ui.adapter.ReleaseAdapter
import com.krunal.demo.githubclient.ui.adapter.RepoDetailAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.view.SpaceItemDecorator
import com.krunal.demo.githubclient.ui.viewmodel.ReleaseViewModel
import com.krunal.demo.githubclient.ui.viewmodel.RepositoryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class ReleaseFragment :
    BaseFragment<FragmentReleaseBinding, ReleaseViewModel>() {

    private lateinit var releaseAdapter: ReleaseAdapter
    private val args: ReleaseFragmentArgs by navArgs()
    override val viewModel: ReleaseViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_release

    override fun initialize() {
        super.initialize()
        setupRepository()
        viewModel.getRepository(args.repositoryName)
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.release.collectLatest { release ->
                    release?.releaseAssets?.let { list ->
                        releaseAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun setupRepository() {
        releaseAdapter = ReleaseAdapter()
        binding.rvRelease.apply {
            adapter = releaseAdapter
            addItemDecoration(SpaceItemDecorator())
        }
        releaseAdapter.itemClickListener = object : ItemClickListener<ReleaseAsset> {
            override fun onClick(item: ReleaseAsset, position: Int) {
                try {
                    val downloadFolder = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                    val file = File(downloadFolder, item.name)
                    viewModel.downloadAsset(item, file)
                } catch (e: IOException) {
                    Toast.makeText(
                        requireContext(),
                        e.localizedMessage ?: getString(R.string.cant_create_file),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}