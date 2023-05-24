package com.krunal.demo.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.FeedVideoLayoutBinding
import com.krunal.demo.recyclerview.models.VideoDetails

class VideoViewHolder(val binding: FeedVideoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(videoDetails: VideoDetails) {
        binding.videoDetail = videoDetails
    }
}