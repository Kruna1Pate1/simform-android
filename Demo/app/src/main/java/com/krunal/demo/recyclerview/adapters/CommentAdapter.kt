package com.krunal.demo.recyclerview.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemCommentBinding
import com.krunal.demo.databinding.ItemRecommendationBinding
import com.krunal.demo.recyclerview.models.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val comments: MutableList<Comment> = mutableListOf()
    var isExpanded: Boolean = false

    class CommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.comment = comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int = if (isExpanded) comments.count() else 1

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun toggleExpanded(expand: Boolean) {
        isExpanded = expand
        if (isExpanded) {
            notifyItemRangeInserted(1, comments.count() - 1)
        } else {
            notifyItemRangeRemoved(1, comments.count() - 1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitComments(list: List<Comment>) {
        comments.clear()
        comments.addAll(list)
        notifyDataSetChanged()
    }
}