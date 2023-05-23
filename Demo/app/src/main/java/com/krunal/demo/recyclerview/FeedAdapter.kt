package com.krunal.demo.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.FeedVideoLayoutBinding
import com.krunal.demo.recyclerview.models.VideoDetails

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.VideoViewHolder>() {

    private val videoDetails: MutableList<VideoDetails> = mutableListOf()

    class VideoViewHolder(val context: Context, val binding: FeedVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedVideoLayoutBinding.inflate(layoutInflater, parent, false)
        return VideoViewHolder(parent.context, binding)
    }

    override fun getItemCount(): Int = 2


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoDetails[position])
    }

    fun submitVideoDetails(details: List<VideoDetails>) {
        videoDetails.clear()
        videoDetails.addAll(details)
        notifyDataSetChanged()
    }
}