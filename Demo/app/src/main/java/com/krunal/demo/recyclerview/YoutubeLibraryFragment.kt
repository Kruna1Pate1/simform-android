package com.krunal.demo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentYoutubeLibraryBinding
import com.krunal.demo.recyclerview.adapters.LibraryAdapter
import com.krunal.demo.recyclerview.listeners.PaginationListener
import com.krunal.demo.recyclerview.viewmodels.YoutubeLibraryFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class YoutubeLibraryFragment : Fragment() {

    private lateinit var binding: FragmentYoutubeLibraryBinding
    private val viewModel: YoutubeLibraryFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentYoutubeLibraryBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.toolBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.actionToggleLayout) {
                it.isChecked = it.isChecked.not()

                binding.rvLibrary.layoutManager = if (it.isChecked) GridLayoutManager(
                    requireContext(), SPAN_COUNT
                ) else LinearLayoutManager(requireContext())
            }
            true
        }
    }

    private fun setupRecyclerView() {
        val libraryAdapter = LibraryAdapter()
        binding.rvLibrary.adapter = libraryAdapter
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvLibrary.layoutManager = layoutManager
        binding.rvLibrary.addOnScrollListener(object : PaginationListener(layoutManager) {

            override val isLastPage: Boolean
                get() = viewModel.isLastPage
            override val isLoading: Boolean
                get() = viewModel.isLoading.value

            override fun loadMoreItems() {
                viewModel.loadMoreData()
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.videoDetails.collectLatest { details ->
                        libraryAdapter.addList(details)
                    }
                }

                launch {
                    viewModel.isLoading.collectLatest { isLoading ->
                        if (isLoading) libraryAdapter.showLoading() else libraryAdapter.hideLoading()
                    }
                }
            }
        }

        viewModel.loadMoreData()
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}