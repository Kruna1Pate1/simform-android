package com.krunal.demo.recyclerview.adapters

import android.net.Uri
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
import com.krunal.demo.recyclerview.models.Payload

class CalculationAdapter : ListAdapter<Calculation, CalculationAdapter.CalculationViewHolder>(CalculationDiffCallback) {

    var onItemChangeListener: OnItemChangeListener? = null

    class CalculationViewHolder(
        private val binding: ItemCalculationBinding
                                , private val onItemChangeListener: OnItemChangeListener?) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(calculation: Calculation) {

            val imageAdapter = CalculationImagesAdapter().apply {
                onLongClick = { imagePosition ->
                    onItemChangeListener?.onImageRemove(adapterPosition, imagePosition)
                }
            }

            val valueAdapter = ValueAdapter { position, value ->
                onItemChangeListener?.onValueChange(adapterPosition, position, value)
            }
            valueAdapter.submitList(calculation.additionalNums)

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
                    if (hasFocus || adapterPosition == -1) return@setOnFocusChangeListener

                    txtNum1.text.toString().toDoubleOrNull()?.let { num1 ->
                        if (num1 != calculation.num1) {
                            onItemChangeListener?.onNumberChange(
                                adapterPosition,
                                num1,
                                null
                            )
                        }
                    }
                }

                txtNum2.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus || adapterPosition == -1) return@setOnFocusChangeListener

                    txtNum2.text.toString().toDoubleOrNull()?.let { num2 ->
                        if (num2 != calculation.num2) {
                            onItemChangeListener?.onNumberChange(
                                adapterPosition,
                                null,
                                num2
                            )
                        }
                    }
                }

                btnRemove.setOnClickListener {
                    onItemChangeListener?.onCalculationRemove(adapterPosition)
                }

                btnAddImage.setOnClickListener {
                    onItemChangeListener?.onImageAdd(adapterPosition)
                }
            }
        }

        fun addImage(uri: Uri) {
            (binding.rvImages.adapter as? CalculationImagesAdapter)?.addImage(uri)
        }

        fun removeImage(position: Int) {
            (binding.rvImages.adapter as? CalculationImagesAdapter)?.removeImage(position)
        }

        fun addValue(value: Int, total: Double) {
            (binding.rvValue.adapter as? ValueAdapter)?.addValue(value)
            setTotal(total)
        }

        fun removeValue(position: Int, total: Double) {
            (binding.rvValue.adapter as? ValueAdapter)?.removeValue(position)
            setTotal(total)
        }

        fun bindNumbers(num1: Double, num2: Double, total: Double) {
            binding.txtNum1.setText(num1.toString())
            binding.txtNum2.setText(num2.toString())
            setTotal(total)
        }

        fun changeValue(position: Int, value: Int, total: Double) {
            (binding.rvImages.adapter as? ValueAdapter)?.changeValue(position, value)
            setTotal(total)
        }

        private fun setTotal(total: Double) {
            binding.tvTotal.text = binding.root.context.getString(R.string.total, total)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCalculationBinding.inflate(layoutInflater)
        return CalculationViewHolder(binding, onItemChangeListener)
    }

    override fun getItemCount(): Int = currentList.count()

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: CalculationViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        (payloads.firstOrNull() as? Payload)?.let { payload ->
            when (payload) {
               is Payload.ChangeNumber -> holder.bindNumbers(payload.num1, payload.num2, currentList[position].total)

                is Payload.AddImage -> holder.addImage(payload.uri)

                is Payload.RemoveImage -> holder.removeImage(payload.position)

                is Payload.AddValue -> holder.addValue(payload.value, currentList[position].total)

                is Payload.RemoveValue -> holder.removeValue(payload.position, currentList[position].total)

                is Payload.ChangeValue -> holder.changeValue(payload.position, payload.value, currentList[position].total)
            }
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}