package com.krunal.demo.appcomponents.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FragmentDemoViewModel: ViewModel() {

    val message: MutableStateFlow<String?> = MutableStateFlow(null)

    fun setMessage(message: String) {
        viewModelScope.launch {
            this@FragmentDemoViewModel.message.emit(message)
        }
    }
}