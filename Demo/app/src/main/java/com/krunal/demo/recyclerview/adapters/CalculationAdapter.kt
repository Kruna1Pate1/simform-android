package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.R
import com.krunal.demo.databinding.ItemCalculationBinding
import com.krunal.demo.recyclerview.decorations.SpaceDecoration
import com.krunal.demo.recyclerview.listeners.CalculationDiffCallback
import com.krunal.demo.recyclerview.listeners.OnItemChangeListener
import com.krunal.demo.recyclerview.models.Calculation

class CalculationAdapter : ListAdapter<Calculation, CalculationAdapter.CalculationViewHolder>(CalculationDiffCallback) {

    var onItemChangeListener: OnItemChangeListener? = null

    class CalculationViewHolder(private val binding: ItemCalculationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val images = listOf(R.drawable.profile, R.drawable.dark_forest, R.drawable.running_up_that_hill, R.drawable.thumbnail1)

        fun bind(calculation: Calculation, onItemChangeListener: OnItemChangeListener?) {
            val valueAdapter = ValueAdapter { position, value ->
                onItemChangeListener?.onValueChange(adapterPosition, position, value)
            }
            valueAdapter.submitList(calculation.additionalNums)

            val imageAdapter = ImagesAdapter().apply {
                submitList(calculation.images)
                onLongClick = { imagePosition ->
                    onItemChangeListener?.onImageRemove(adapterPosition, imagePosition)
                }
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = true

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    onItemChangeListener?.onValueRemove(adapterPosition, viewHolder.adapterPosition)
                }
            }).attachToRecyclerView(binding.rvValue)

            binding.apply {
                this.calculation = calculation
                rvValue.adapter = valueAdapter
                rvImages.adapter = imageAdapter
                rvImages.addItemDecoration(SpaceDecoration(SpaceDecoration.HORIZONTAL))

                btnAddValue.setOnClickListener {
                    onItemChangeListener?.onValueAdd(adapterPosition, 1)
                }

                txtNum1.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) return@setOnFocusChangeListener

                    txtNum1.text.toString().toDoubleOrNull()?.let { num1 ->
                        if (num1 != calculation.num1) {
                            onItemChangeListener?.onNumberChange(
                                adapterPosition,
                                num1,
                                calculation.num2
                            )
                        }
                    }
                }

                txtNum2.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) return@setOnFocusChangeListener

                    txtNum2.text.toString().toDoubleOrNull()?.let { num2 ->
                        if (num2 != calculation.num2) {
                            onItemChangeListener?.onNumberChange(
                                adapterPosition,
                                calculation.num1,
                                num2
                            )
                        }
                    }
                }

                btnRemove.setOnClickListener {
                    onItemChangeListener?.onCalculationRemove(adapterPosition)
                }

                btnAddImage.setOnClickListener {
                    onItemChangeListener?.onImageAdd(adapterPosition, images.random())
                }
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
}