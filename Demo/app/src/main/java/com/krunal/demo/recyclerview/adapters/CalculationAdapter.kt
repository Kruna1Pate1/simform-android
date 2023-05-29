package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemCalculationBinding
import com.krunal.demo.recyclerview.models.Calculation

class CalculationAdapter : RecyclerView.Adapter<CalculationAdapter.CalculationViewHolder>() {

    private val calculations: MutableList<Calculation> = mutableListOf()

    class CalculationViewHolder(private val binding: ItemCalculationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(calculation: Calculation) {
            binding.calculation = calculation
            binding.rvValue.adapter = ValueAdapter().apply {
                submitList(calculation.additionalNums)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCalculationBinding.inflate(layoutInflater)
        return CalculationViewHolder(binding)
    }

    override fun getItemCount(): Int = calculations.count()

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        holder.bind(calculations[position])
    }

    fun submitList(list: List<Calculation>) {
        calculations.clear()
        calculations.addAll(list)
        notifyDataSetChanged()
    }
}