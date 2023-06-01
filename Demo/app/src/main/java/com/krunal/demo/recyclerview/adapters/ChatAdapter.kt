package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemMessageReceiveBinding
import com.krunal.demo.databinding.ItemMessageSendBinding
import com.krunal.demo.recyclerview.listeners.ChatDiffCallback
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.models.enums.MessageType

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages: MutableList<Message> = mutableListOf()

    class SendMessageViewHolder(private val binding: ItemMessageSendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message, onRemove: (Message) -> Unit) {
            binding.message = message
            binding.root.setOnLongClickListener {
                onRemove(message)
                true
            }
        }
    }

    class ReceiveMessageViewHolder(private val binding: ItemMessageReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message, onRemove: (Message) -> Unit) {
            binding.message = message
            binding.root.setOnLongClickListener {
                onRemove(message)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (MessageType.values()[viewType]) {
            MessageType.SEND -> {
                val binding = ItemMessageSendBinding.inflate(layoutInflater)
                SendMessageViewHolder(binding)
            }

            MessageType.RECEIVE -> {
                val binding = ItemMessageReceiveBinding.inflate(layoutInflater)
                ReceiveMessageViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = messages.count()

    override fun getItemViewType(position: Int): Int = messages[position].messageType.ordinal

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val onRemove: (Message) -> Unit = { message ->
            submitList(messages.filterNot { it == message })
        }

        when (holder) {
            is SendMessageViewHolder -> {
                holder.bind(messages[position], onRemove)
            }

            is ReceiveMessageViewHolder -> {
                holder.bind(messages[position], onRemove)
            }
        }
    }

    fun submitList(list: List<Message>) {
        val diffUtil = ChatDiffCallback(this.messages, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        messages.clear()
        messages.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }
}

