package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.Feed
import com.krunal.demo.recyclerview.models.VideoDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YoutubeHomeFragmentViewModel : ViewModel() {

    private val _videoDetails: MutableStateFlow<List<Feed>> = MutableStateFlow(emptyList())
    val videoDetails: StateFlow<List<Feed>> = _videoDetails

    init {
        setupInitialValue()
    }

    private fun setupInitialValue() {
        viewModelScope.launch {
            delay(1000)
            _videoDetails.emit(
                Feed.dummyData
            )
        }
    }
}