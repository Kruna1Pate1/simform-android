package com.krunal.demo.recyclerview.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.CommunityPostLayoutBinding
import com.krunal.demo.databinding.FeedVideoLayoutBinding
import com.krunal.demo.databinding.ShortVideoLayoutBinding
import com.krunal.demo.recyclerview.models.CommunityPost
import com.krunal.demo.recyclerview.models.Feed
import com.krunal.demo.recyclerview.models.FeedType
import com.krunal.demo.recyclerview.models.VideoDetails

class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val feeds: MutableList<Feed> = mutableListOf()

    class VideoViewHolder(val context: Context, val binding: FeedVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    class ShortVideoViewHolder(val context: Context, val binding: ShortVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    class CommunityPostViewHolder(val context: Context, val binding: CommunityPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            binding.communityPost = communityPost
            binding.rvImage.adapter = ImagesAdapter().apply {
                submitList(communityPost.images)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (FeedType.values()[viewType]) {
            FeedType.VIDEO -> {
                val binding = FeedVideoLayoutBinding.inflate(layoutInflater, parent, false)
                VideoViewHolder(parent.context, binding)
            }

            FeedType.SHORT_VIDEO -> {
                val binding = ShortVideoLayoutBinding.inflate(layoutInflater, parent, false)
                ShortVideoViewHolder(parent.context, binding)
            }

            FeedType.PLAYLIST -> TODO()
            FeedType.COMMUNITY_POST -> {
                val binding = CommunityPostLayoutBinding.inflate(layoutInflater, parent, false)
                CommunityPostViewHolder(parent.context, binding)
            }
        }
    }

    override fun getItemCount(): Int = feeds.count()

    override fun getItemViewType(position: Int): Int {
        return feeds[position].type.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val details = feeds[position]
        when (holder) {
            is VideoViewHolder -> {
                holder.bind(details as VideoDetails)
            }

            is ShortVideoViewHolder -> {
                holder.bind(details as VideoDetails)
            }

            is CommunityPostViewHolder -> {
                holder.bind(details as CommunityPost)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitFeeds(details: List<Feed>) {
        feeds.clear()
        feeds.addAll(details)
        notifyDataSetChanged()
    }
}