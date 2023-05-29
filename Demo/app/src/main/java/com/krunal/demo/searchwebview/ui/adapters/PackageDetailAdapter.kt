package com.krunal.demo.searchwebview.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemPackageDetailBinding
import com.krunal.demo.searchwebview.data.models.PackageDetail

class PackageDetailAdapter(private val onClick: (packageDetail: PackageDetail) -> Unit) :
    RecyclerView.Adapter<PackageDetailAdapter.PackageDetailsViewHolder>(), Filterable {

    private val filteredPackageDetails: MutableList<PackageDetail> = mutableListOf()
    private val originalPackageDetails: MutableList<PackageDetail> = mutableListOf()

    class PackageDetailsViewHolder(
        private val binding: ItemPackageDetailBinding, private val onClick: (packageDetail: PackageDetail) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(packageDetail: PackageDetail) {
            binding.packageDetail = packageDetail
            binding.root.setOnClickListener {
                onClick(packageDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPackageDetailBinding.inflate(layoutInflater, parent, false)
        return PackageDetailsViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = filteredPackageDetails.count()

    override fun onBindViewHolder(holder: PackageDetailsViewHolder, position: Int) {
        holder.bind(filteredPackageDetails[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<PackageDetail>) {
        originalPackageDetails.clear()
        originalPackageDetails.addAll(list)
        filteredPackageDetails.clear()
        filteredPackageDetails.addAll(list)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterString = constraint.toString().lowercase()
            val filteredList =
                originalPackageDetails.filter { it.name.contains(filterString, true) }

            return FilterResults().apply {
                values = filteredList
                count = filteredList.count()
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            (results?.values as? List<PackageDetail>)?.let { filteredList ->
                filteredPackageDetails.clear()
                filteredPackageDetails.addAll(filteredList)
            }
            notifyDataSetChanged()
        }
    }
}