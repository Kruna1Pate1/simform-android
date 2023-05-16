package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.listeners.PaginationListener
import com.krunal.demo.recyclerview.models.VideoDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YoutubeLibraryFragmentViewModel : ViewModel() {


    private val _videoDetails: MutableSharedFlow<List<VideoDetails>> = MutableSharedFlow()
    val videoDetails: SharedFlow<List<VideoDetails>> = _videoDetails

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    val isLastPage: Boolean
        get() = false //videoDetails.value.size / PaginationListener.PAGE_SIZE < currentPage
    var currentPage: Int = 0
        private set

    fun loadMoreData() {
        viewModelScope.launch {
            _isLoading.emit(true)
            delay(1500)
            _videoDetails.emit(VideoDetails.dummyData)
            currentPage++
            _isLoading.emit(false)
        }
    }
}
