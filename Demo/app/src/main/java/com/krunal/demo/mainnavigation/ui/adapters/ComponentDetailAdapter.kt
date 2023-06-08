package com.krunal.demo.mainnavigation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemComponentDetailBinding
import com.krunal.demo.mainnavigation.data.models.ComponentDetail

class ComponentDetailAdapter(private val onClick: (componentDetail: ComponentDetail) -> Unit) :
    RecyclerView.Adapter<ComponentDetailAdapter.ComponentDetailsViewHolder>() {

    private val componentDetails: MutableList<ComponentDetail> = mutableListOf()

    class ComponentDetailsViewHolder(
        private val binding: ItemComponentDetailBinding,
        private val onClick: (componentDetail: ComponentDetail) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(componentDetail: ComponentDetail) {
            binding.componentDetail = componentDetail
            binding.root.setOnClickListener {
                onClick(componentDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemComponentDetailBinding.inflate(layoutInflater, parent, false)
        return ComponentDetailsViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = componentDetails.count()

    override fun onBindViewHolder(holder: ComponentDetailsViewHolder, position: Int) {
        holder.bind(componentDetails[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ComponentDetail>) {
        componentDetails.clear()
        componentDetails.addAll(list)
        notifyDataSetChanged()
    }
}