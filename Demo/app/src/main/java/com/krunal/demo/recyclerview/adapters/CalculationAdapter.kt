package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemCalculationBinding
import com.krunal.demo.recyclerview.listeners.CalculationDiffCallback
import com.krunal.demo.recyclerview.listeners.OnItemChangeListener
import com.krunal.demo.recyclerview.models.Calculation

class CalculationAdapter : ListAdapter<Calculation, CalculationAdapter.CalculationViewHolder>(CalculationDiffCallback) {

    var onItemChangeListener: OnItemChangeListener? = null

    class CalculationViewHolder(private val binding: ItemCalculationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(calculation: Calculation, onItemChangeListener: OnItemChangeListener?) {
            binding.calculation = calculation
            val adapter = ValueAdapter { position, value ->
                onItemChangeListener?.onValueChange(adapterPosition, position, value)
            }
            adapter.submitList(calculation.additionalNums)
            binding.rvValue.adapter = adapter

            binding.btnAddValue.setOnClickListener {
                onItemChangeListener?.onValueAdd(adapterPosition, 1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCalculationBinding.inflate(layoutInflater)
        return CalculationViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.count()

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        holder.bind(currentList[position], onItemChangeListener)
    }

//    fun submitList(list: List<Calculation>) {
//        calculations.clear()
//        calculations.addAll(list)
//        notifyDataSetChanged()
//    }
//
//    fun updateItem(item: Calculation, position: Int) {
//        calculations[position] = item
//        notifyItemChanged(position)
//    }
//
//    fun removeItem(position: Int) {
//        calculations.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun addItem(item: Calculation, position: Int) {
//        calculations.add(position, item)
//        notifyItemInserted(position)
//    }
}