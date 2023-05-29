package com.krunal.demo.stackexchange.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemPortfolioBinding
import com.krunal.demo.stackexchange.models.ShareDetails

class PortfolioAdapter : RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    private val shareDetailsList: MutableList<ShareDetails> = mutableListOf()

    class PortfolioViewHolder(private val binding: ItemPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shareDetails: ShareDetails) {
            binding.shareDetails = shareDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPortfolioBinding.inflate(layoutInflater, parent, false)
        return PortfolioViewHolder(binding)
    }

    override fun getItemCount(): Int = shareDetailsList.count()

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(shareDetailsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ShareDetails>) {
        shareDetailsList.clear()
        shareDetailsList.addAll(list)
        notifyDataSetChanged()
    }
}