package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemHistoryVideoBinding
import com.krunal.demo.databinding.ItemLoadingBinding
import com.krunal.demo.recyclerview.models.HistoryViewType
import com.krunal.demo.recyclerview.models.VideoDetails

class LibraryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val videoDetails: MutableList<VideoDetails> = mutableListOf()
    var isLoading: Boolean = false

    class HistoryViewHolder(val binding: ItemHistoryVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    class LoadingViewHolder(val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(isLoading: Boolean) {
            binding.isLoading = isLoading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (HistoryViewType.values()[viewType]) {
            HistoryViewType.VIDEO -> {
                val binding = ItemHistoryVideoBinding.inflate(layoutInflater, parent, false)
                HistoryViewHolder(binding)
            }

            HistoryViewType.LOADING -> {
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }

    }

    override fun getItemCount(): Int = if (isLoading) {
        videoDetails.count() + 1
    } else {
        videoDetails.count()
    }

    override fun getItemViewType(position: Int): Int =
        (if (isLoading && position == videoDetails.count()) HistoryViewType.LOADING else HistoryViewType.VIDEO).ordinal

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryViewHolder -> holder.bind(videoDetails[position])
            is LoadingViewHolder -> holder.bind(isLoading)
        }
    }

    fun showLoading() {
        isLoading = true
        notifyItemInserted(videoDetails.count())
    }

    fun hideLoading() {
        isLoading = false
        notifyItemRemoved(videoDetails.count())
    }

    fun submitList(list: List<VideoDetails>) {
        videoDetails.clear()
        videoDetails.addAll(list)
        notifyDataSetChanged()
    }

    fun addList(list: List<VideoDetails>) {
        videoDetails.addAll(list)
//        notifyItemRangeInserted(videoDetails.count() - list.count(), list.count())
        notifyDataSetChanged()
    }
}