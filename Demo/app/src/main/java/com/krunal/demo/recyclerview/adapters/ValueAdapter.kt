package com.krunal.demo.recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemChooseValueBinding

class ValueAdapter : RecyclerView.Adapter<ValueAdapter.ValueViewHolder>() {

    private val additionValues: MutableList<Int> = mutableListOf()

    class ValueViewHolder(
        private val binding: ItemChooseValueBinding, private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        private val values: List<Int> = (1..10).toList()

        fun bind(number: Int) {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, values)
            binding.spinnerValue.setAdapter(adapter)
            binding.number = number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChooseValueBinding.inflate(layoutInflater)
        return ValueViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = additionValues.count()

    override fun onBindViewHolder(holder: ValueViewHolder, position: Int) {
        holder.bind(additionValues[position])
    }

    fun submitList(list: List<Int>) {
        additionValues.clear()
        additionValues.addAll(list)
        notifyDataSetChanged()
    }
}