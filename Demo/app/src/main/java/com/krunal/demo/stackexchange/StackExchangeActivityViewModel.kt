package com.krunal.demo.stackexchange

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StackExchangeActivityViewModel : ViewModel() {

    private val _notificationCount: MutableStateFlow<Int> = MutableStateFlow(6)
    val notificationCount: StateFlow<Int> = _notificationCount
}