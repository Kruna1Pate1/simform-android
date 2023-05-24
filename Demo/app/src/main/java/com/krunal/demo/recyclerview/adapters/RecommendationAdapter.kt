package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.FeedVideoLayoutBinding
import com.krunal.demo.recyclerview.models.VideoDetails
import com.krunal.demo.recyclerview.viewholders.VideoViewHolder

class RecommendationAdapter : RecyclerView.Adapter<VideoViewHolder>() {

    private val videos: MutableList<VideoDetails> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedVideoLayoutBinding.inflate(layoutInflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int = videos.count()

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    fun submitList(list: List<VideoDetails>) {
        videos.clear()
        videos.addAll(list)
        notifyDataSetChanged()
    }
}