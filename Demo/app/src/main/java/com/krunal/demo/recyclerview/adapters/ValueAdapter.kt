package com.krunal.demo.recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemChooseValueBinding

class ValueAdapter(private val onValueChange: (position: Int, value: Int) -> Unit) :
    RecyclerView.Adapter<ValueAdapter.ValueViewHolder>() {

    private val additionValues: MutableList<Int> = mutableListOf()

    class ValueViewHolder(
        private val binding: ItemChooseValueBinding, private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        private val values: List<Int> = (1..10).toList()

        fun bind(number: Int, onValueChange: (Int, Int) -> Unit) {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, values)
            binding.spinnerValue.setAdapter(adapter)
            binding.spinnerValue.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    onValueChange(adapterPosition, values[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChooseValueBinding.inflate(layoutInflater)
        return ValueViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = additionValues.count()

    override fun onBindViewHolder(holder: ValueViewHolder, position: Int) {
        holder.bind(additionValues[position], onValueChange)
    }

    fun submitList(list: List<Int>) {
        additionValues.clear()
        additionValues.addAll(list)
        notifyDataSetChanged()
    }


    fun removeItem(position: Int) {
        additionValues.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(item: Int, position: Int) {
        additionValues.add(position, item)
        notifyItemInserted(position)
    }
}