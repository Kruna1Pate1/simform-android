package com.krunal.demo.recyclerview.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.krunal.demo.databinding.CommunityPostLayoutBinding
import com.krunal.demo.databinding.FeedVideoLayoutBinding
import com.krunal.demo.databinding.ItemRecommendationBinding
import com.krunal.demo.databinding.ShortVideoLayoutBinding
import com.krunal.demo.recyclerview.models.CommunityPost
import com.krunal.demo.recyclerview.models.Feed
import com.krunal.demo.recyclerview.models.FeedType
import com.krunal.demo.recyclerview.models.Recommendation
import com.krunal.demo.recyclerview.models.VideoDetails
import com.krunal.demo.recyclerview.viewholders.VideoViewHolder

class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val feeds: MutableList<Feed> = mutableListOf()

    class ShortVideoViewHolder(val binding: ShortVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDetails: VideoDetails) {
            binding.videoDetail = videoDetails
        }
    }

    class RecommendationViewHolder(val context: Context, val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recommendation: Recommendation) {
            binding.recommendation = recommendation
            binding.rvRecommendation.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvRecommendation.adapter =
                RecommendationAdapter().apply { submitList(recommendation.videos) }

            LinearSnapHelper().also { snapHelper ->
                snapHelper.attachToRecyclerView(binding.rvRecommendation)
            }
            binding.rvRecommendation.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    c.drawCircle(10f, 10f, 10F, Paint().apply { color = Color.RED })
                }

                override fun onDrawOver(
                    c: Canvas,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.onDrawOver(c, parent, state)
                }
            })
        }
    }

    class CommunityPostViewHolder(val binding: CommunityPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(communityPost: CommunityPost) {
            binding.communityPost = communityPost
            (binding.vpImage.getChildAt(0) as? RecyclerView)?.clearOnChildAttachStateChangeListeners()
            binding.vpImage.adapter = ImagesAdapter().apply {
                submitList(communityPost.images)
            }

            TabLayoutMediator(binding.tlIndicator, binding.vpImage) { _, _ -> }.attach()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (FeedType.values()[viewType]) {
            FeedType.VIDEO -> {
                val binding = FeedVideoLayoutBinding.inflate(layoutInflater, parent, false)
                VideoViewHolder(binding)
            }

            FeedType.SHORT_VIDEO -> {
                val binding = ShortVideoLayoutBinding.inflate(layoutInflater, parent, false)
                ShortVideoViewHolder(binding)
            }

            FeedType.RECOMMENDATION -> {
                val binding = ItemRecommendationBinding.inflate(layoutInflater, parent, false)
                RecommendationViewHolder(parent.context, binding)
            }

            FeedType.COMMUNITY_POST -> {
                val binding = CommunityPostLayoutBinding.inflate(layoutInflater, parent, false)
                CommunityPostViewHolder(binding)
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

            is RecommendationViewHolder -> {
                holder.bind(details as Recommendation)
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