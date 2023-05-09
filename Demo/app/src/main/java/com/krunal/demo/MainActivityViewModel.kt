package com.krunal.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivityViewModel : ViewModel() {

    private val _timeFlow = MutableStateFlow<String?>(null)
    val timeFlow: StateFlow<String?> = _timeFlow

    init {
        start()
    }

    private fun start() {
        viewModelScope.launch {
            while (true) {
                _timeFlow.emit(currTime)
            }
        }
    }

    private val currTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }
}