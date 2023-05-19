package com.krunal.demo.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.krunal.demo.databinding.FragmentYoutubeHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class YoutubeHomeFragment : Fragment() {

    private lateinit var binding: FragmentYoutubeHomeBinding
    private val viewModel: YoutubeHomeFragmentViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentYoutubeHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        feedAdapter = FeedAdapter()
        feedAdapter.submitVideoDetails(viewModel.videoDetails.value)
        binding.rvHome.adapter = feedAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.videoDetails.collectLatest { videoDetails ->
                    feedAdapter.submitVideoDetails(videoDetails)
                }
            }
        }
    }
}