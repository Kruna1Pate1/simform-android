package com.krunal.demo.searchwebview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemPackageDetailBinding
import com.krunal.demo.searchwebview.data.models.PackageDetail

class PackageDetailAdapter(private val onClick: (userId: Int) -> Unit) :
    RecyclerView.Adapter<PackageDetailAdapter.PackageDetailsViewHolder>() {

    private val packageDetails: MutableList<PackageDetail> = mutableListOf()

    class PackageDetailsViewHolder(
        private val binding: ItemPackageDetailBinding, private val onClick: (userId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(packageDetail: PackageDetail) {
            binding.packageDetail = packageDetail
            binding.root.setOnClickListener {
                // TODO: Handle package onClick
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPackageDetailBinding.inflate(layoutInflater, parent, false)
        return PackageDetailsViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = packageDetails.count()

    override fun onBindViewHolder(holder: PackageDetailsViewHolder, position: Int) {
        holder.bind(packageDetails[position])
    }

    fun submitList(list: List<PackageDetail>) {
        packageDetails.clear()
        packageDetails.addAll(list)
        notifyDataSetChanged()
    }
}