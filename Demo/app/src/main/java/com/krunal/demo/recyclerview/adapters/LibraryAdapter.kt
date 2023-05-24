package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemHistoryVideoBinding
import com.krunal.demo.recyclerview.models.VideoDetails

class LibraryAdapter : RecyclerView.Adapter<LibraryAdapter.HistoryViewHolder>() {

    private val videoDetails: MutableList<VideoDetails> = mutableListOf()

    class HistoryViewHolder(val binding: ItemHistoryVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryVideoBinding.inflate(layoutInflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = videoDetails.count()

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(videoDetails[position])
    }

    fun submitList(list: List<VideoDetails>) {
        videoDetails.clear()
        videoDetails.addAll(list)
        notifyDataSetChanged()
    }
}