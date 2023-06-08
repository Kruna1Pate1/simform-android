package com.krunal.demo.searchwebview.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.searchwebview.data.repositories.WebViewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WebViewViewModel : ViewModel() {

    private val _url: MutableStateFlow<String> = MutableStateFlow("")
    val url: StateFlow<String> = _url

    init {
        loadUrl(WebViewRepository.getRandomUrl())
    }

    fun loadUrl(url: String) {
        viewModelScope.launch {
            _url.emit(url)
        }
    }
}