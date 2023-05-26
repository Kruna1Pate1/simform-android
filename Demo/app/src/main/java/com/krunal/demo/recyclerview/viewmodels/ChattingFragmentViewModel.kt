package com.krunal.demo.recyclerview.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import com.krunal.demo.R
import com.krunal.demo.recyclerview.listeners.ChatDiffCallback
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.models.MessageType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ChattingFragmentViewModel : ViewModel() {

    val messages: MutableList<Message> = mutableListOf()

    private val _messageFlow: MutableSharedFlow<Message> = MutableSharedFlow()
    val messageFlow: SharedFlow<Message> = _messageFlow

    val message: MutableStateFlow<String> = MutableStateFlow("")

    private val sender = "Krunal"
    private val receiver = "Harsh"

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

    fun sendMessage(view: View) {
        viewModelScope.launch {
            _messageFlow.emit(
                Message(
                    sender, R.drawable.profile, message.value, MessageType.SEND
                )
            )
            message.value = ""
        }
    }

    fun receiveMessage(view: View) {
        viewModelScope.launch {
            _messageFlow.emit(
                Message(
                    receiver, R.drawable.android_dev, message.value, MessageType.RECEIVE
                )
            )
            message.value = ""
        }
    }
}