package com.krunal.demo.webservices.withoutlibrary.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.databinding.FragmentNewsListBinding
import com.krunal.demo.webservices.withoutlibrary.ui.adapters.NewsAdapter
import com.krunal.demo.webservices.withoutlibrary.ui.viewmodels.NewsViewModel
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
        val adapter = NewsAdapter { newsItem ->
            Intent(Intent.ACTION_VIEW, newsItem.url.toUri()).also { intent ->
                activity?.startActivity(intent)
            }
        }

        binding.rvNewsList.apply {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.newsList.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }
}