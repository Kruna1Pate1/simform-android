package com.krunal.demo.recyclerview.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.R
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.models.MessageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ChattingFragmentViewModel : ViewModel() {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: SharedFlow<List<Message>> = _messages

    val message: MutableStateFlow<String> = MutableStateFlow("")

    private val sender = "Krunal"
    private val receiver = "Harsh"

    init {
        setupMessages()
    }

    private fun setupMessages() {
        viewModelScope.launch {
            _messages.emit(Message.dummyData)
        }
    }

    fun sendMessage(view: View) {
        viewModelScope.launch {
            _messages.emit(
                _messages.value + Message(
                    sender, R.drawable.profile, message.value, MessageType.SEND
                )
            )
            message.value = ""
        }
    }

    fun receiveMessage(view: View) {
        viewModelScope.launch {
            _messages.emit(
                _messages.value + Message(
                    receiver, R.drawable.android_dev, message.value, MessageType.RECEIVE
                )
            )
            message.value = ""
        }
    }

    fun removeMessage(position: Int) {
        viewModelScope.launch {
            _messages.emit(_messages.value - _messages.value[position])
        }
    }
}