package com.krunal.demo.webservices.withoutlibrary.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemNewsBinding
import com.krunal.demo.webservices.withoutlibrary.data.models.local.NewsItem

class NewsAdapter(val onClick: (newsItem: NewsItem) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList: MutableList<NewsItem> = mutableListOf()

    class NewsViewHolder(
        private val binding: ItemNewsBinding,
        val onClick: (NewsItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsItem) {
            binding.newsItem = newsItem
            binding.root.setOnClickListener {
                onClick(newsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = newsList.count()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<NewsItem>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}