package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.ChatDetail
import com.krunal.demo.recyclerview.models.ContactDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatDetailsViewModel : ViewModel() {

    private val _chatDetails: MutableStateFlow<List<ChatDetail>> =
        MutableStateFlow(emptyList())
    val chatDetails: StateFlow<List<ChatDetail>> = _chatDetails

    private val _currentDetail: MutableStateFlow<ChatDetail?> = MutableStateFlow(null)
    val currentDetail: StateFlow<ChatDetail?> = _currentDetail

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _chatDetails.emit(ChatDetail.dummyDetails)
        }
    }

    fun changeContactDetail(position: Int) {
        viewModelScope.launch {
            _currentDetail.emit(chatDetails.value[position])
        }
    }
}