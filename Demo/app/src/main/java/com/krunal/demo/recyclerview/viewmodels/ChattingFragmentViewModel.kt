package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.Message
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ChattingFragmentViewModel : ViewModel() {

    val messages: MutableList<Message> = mutableListOf()

    private val _messageFlow: MutableSharedFlow<Message> = MutableSharedFlow()
    val messageFlow: SharedFlow<Message> = _messageFlow

    init {
        setupMessages()
    }

    private fun setupMessages() {
        viewModelScope.launch {
            messageFlow.collect { message ->
                messages.add(message)
            }
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            _messageFlow.emit(message)
        }
    }
}