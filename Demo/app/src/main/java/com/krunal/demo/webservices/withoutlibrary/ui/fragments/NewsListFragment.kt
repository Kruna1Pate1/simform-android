package com.krunal.demo.webservices.withoutlibrary.ui.fragments

import android.app.SearchManager
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentNewsListBinding
import com.krunal.demo.searchwebview.ui.adapters.PackageDetailAdapter
import com.krunal.demo.webservices.withoutlibrary.ui.adapters.NewsAdapter
import com.krunal.demo.webservices.withoutlibrary.ui.viewmodels.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val adapter = NewsAdapter()

        binding.rvNewsList.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.newsList.collectLatest { list ->
                    adapter.submitList(list)
                }
            }
        }
    }
}