package com.krunal.demo.recyclerview.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemHistoryVideoBinding
import com.krunal.demo.databinding.ItemLoadingBinding
import com.krunal.demo.recyclerview.models.enums.HistoryViewType
import com.krunal.demo.recyclerview.models.VideoDetails

class LibraryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val videoDetails: MutableList<VideoDetails> = mutableListOf()
    var isLoading: Boolean = false
    var onAttach = true

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

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                onAttach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        super.onAttachedToRecyclerView(recyclerView)
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


    private fun setAnimation(itemView: View, position: Int) {
        var i = position
        if (!onAttach) {
            i = -1
        }
        val isNotFirstItem = i == -1
        i++
        itemView.translationX = itemView.x + 400
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animatorTranslateY =
            ObjectAnimator.ofFloat(itemView, "translationX", itemView.x + 400, 0f)
        val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animatorTranslateY.startDelay = if (isNotFirstItem) ANIMATION_DURATION else i * ANIMATION_DURATION
        animatorTranslateY.duration = (if (isNotFirstItem) 2 else 1) * ANIMATION_DURATION
        animatorSet.playTogether(animatorTranslateY, animatorAlpha)
        animatorSet.start()
    }

    companion object {
        private const val ANIMATION_DURATION = 500L
    }
}