package com.krunal.demo.searchwebview.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _suggestions: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val suggestions: StateFlow<List<String>> = _suggestions

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
//            _suggestions.emit()
        }
    }
}