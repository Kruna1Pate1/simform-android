package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemMessageReceiveBinding
import com.krunal.demo.databinding.ItemMessageSendBinding
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.models.MessageType

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages: MutableList<Message> = mutableListOf()

    class SendMessageViewHolder(private val binding: ItemMessageSendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.message = message
        }
    }

    class ReceiveMessageViewHolder(private val binding: ItemMessageReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.message = message
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
        when (holder) {
            is SendMessageViewHolder -> {
                holder.bind(messages[position])
            }

            is ReceiveMessageViewHolder -> {
                holder.bind(messages[position])
            }
        }
    }

    fun addMessage(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.count())
    }

    fun removeMessage(message: Message) {
        val position = messages.indexOf(message)
        messages.remove(message)
        notifyItemRemoved(position)
    }

    fun submitList(list: List<Message>) {
        messages.clear()
        messages.addAll(list)
        notifyDataSetChanged()
    }
}