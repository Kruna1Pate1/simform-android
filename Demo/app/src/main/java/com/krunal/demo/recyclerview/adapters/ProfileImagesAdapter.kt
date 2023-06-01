package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemPlusCountBinding
import com.krunal.demo.databinding.ItemProfileImageBinding
import com.krunal.demo.recyclerview.listeners.OnChatChangeListener
import com.krunal.demo.recyclerview.models.enums.ProfileViewType
import kotlin.math.min

class ProfileImagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val profiles: MutableList<Int> = mutableListOf()
    var onChatChangeListener: OnChatChangeListener? = null
    var shouldTruncate: Boolean = false

    class ImageViewHolder(
        private val binding: ItemProfileImageBinding,
        private val onChatChangeListener: OnChatChangeListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Int) {
            binding.imgViewProfile.setImageResource(image)
            binding.root.setOnClickListener {
                onChatChangeListener?.onProfileClick(adapterPosition)
            }
        }
    }

    class CountViewHolder(
        private val binding: ItemPlusCountBinding,
        private val onExpand: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(count: Int) {
            binding.count = count
            binding.root.setOnClickListener {
                onExpand()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (ProfileViewType.values()[viewType]) {
            ProfileViewType.IMAGE -> {
                val binding = ItemProfileImageBinding.inflate(layoutInflater)
                ImageViewHolder(binding, onChatChangeListener)
            }

            ProfileViewType.COUNT -> {
                val binding = ItemPlusCountBinding.inflate(layoutInflater)
                return CountViewHolder(binding) {
                    shouldTruncate = false
                    notifyItemRangeChanged(MAXIMUM_CHAT_COUNT, profiles.count())
                }
            }
        }

    }

    override fun getItemCount(): Int =
        if (shouldTruncate) min(profiles.count(), MAXIMUM_CHAT_COUNT) else profiles.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> {
                holder.bind(profiles[position])
            }

            is CountViewHolder -> {
                holder.bind(profiles.count() - MAXIMUM_CHAT_COUNT)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        (if (shouldTruncate && profiles.count() - 1 != position && position == MAXIMUM_CHAT_COUNT - 1) ProfileViewType.COUNT else ProfileViewType.IMAGE).ordinal

    fun submitList(list: List<Int>) {
        profiles.clear()
        profiles.addAll(list)
        notifyDataSetChanged()
    }

    companion object {
        const val MAXIMUM_CHAT_COUNT = 4
    }
}