package com.krunal.demo.stackexchange.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemShareDetailsBinding
import com.krunal.demo.stackexchange.models.ShareDetails

class ShareDetailsAdapter : RecyclerView.Adapter<ShareDetailsAdapter.ShareDetailsViewHolder>() {

    private val shareDetailsList: MutableList<ShareDetails> = mutableListOf()

    class ShareDetailsViewHolder(private val binding: ItemShareDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shareDetails: ShareDetails) {
            binding.shareDetails = shareDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemShareDetailsBinding.inflate(layoutInflater, parent, false)
        return ShareDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int = shareDetailsList.count()

    override fun onBindViewHolder(holder: ShareDetailsViewHolder, position: Int) {
        holder.bind(shareDetailsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ShareDetails>) {
        shareDetailsList.clear()
        shareDetailsList.addAll(list)
        notifyDataSetChanged()
    }
}