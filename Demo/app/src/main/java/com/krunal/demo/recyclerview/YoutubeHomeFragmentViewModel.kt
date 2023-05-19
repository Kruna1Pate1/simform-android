package com.krunal.demo.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.VideoDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YoutubeHomeFragmentViewModel : ViewModel() {

    private val _videoDetails: MutableStateFlow<List<VideoDetails>> = MutableStateFlow(emptyList())
    val videoDetails: StateFlow<List<VideoDetails>> = _videoDetails

    init {
        setupInitialValue()
    }

    private fun setupInitialValue() {
        viewModelScope.launch {
            _videoDetails.emit(
                VideoDetails.dummyData
            )
        }
    }
}