package com.krunal.demo.recyclerview.listeners

import androidx.recyclerview.widget.DiffUtil
import com.krunal.demo.recyclerview.models.Message

class ChatDiffCallback(
    private val oldMessages: List<Message>,
    private val newMessages: List<Message>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldMessages.count()

    override fun getNewListSize(): Int = newMessages.count()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldMessages[oldItemPosition] === newMessages[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldMessages[oldItemPosition] == newMessages[newItemPosition]
}