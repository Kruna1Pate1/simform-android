package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

data class Message(
    val name: String,
    @DrawableRes val profileImage: Int,
    val text: String,
    val messageType: MessageType
) {

    companion object {
        val dummyData: List<Message> = listOf(
            Message("Sender", R.drawable.profile, "Hi", MessageType.SEND),
            Message("Receiver", R.drawable.android_dev, "Hello", MessageType.RECEIVE)
        )
    }
}