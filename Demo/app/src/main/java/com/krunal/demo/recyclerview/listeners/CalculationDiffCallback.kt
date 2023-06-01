package com.krunal.demo.recyclerview.listeners

import androidx.recyclerview.widget.DiffUtil
import com.krunal.demo.recyclerview.models.Calculation
import com.krunal.demo.recyclerview.models.Payload

object CalculationDiffCallback : DiffUtil.ItemCallback<Calculation>() {

    override fun areItemsTheSame(oldItem: Calculation, newItem: Calculation): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Calculation, newItem: Calculation): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Calculation, newItem: Calculation): Any? = when {
        newItem.num1 != oldItem.num1 -> Payload.ChangeNumber(
            newItem.num1, oldItem.num2
        )

        newItem.num2 != oldItem.num2 -> Payload.ChangeNumber(oldItem.num1, newItem.num2)

        newItem.images.count() == oldItem.images.count() - 1 -> Payload.RemoveImage(
            oldItem.images.withIndex().indexOfFirst { (index, value) ->
                    value != newItem.images.elementAtOrNull(index)
                })

        newItem.images.count() == oldItem.images.count() + 1 -> Payload.AddImage(newItem.images.last())

        newItem.additionalNums.count() == oldItem.additionalNums.count() - 1 -> null

        newItem.additionalNums.count() == oldItem.additionalNums.count() + 1 -> Payload.AddValue(1)

        newItem.additionalNums != oldItem.additionalNums -> {
            val index = newItem.additionalNums.withIndex().indexOfFirst { (index, value) ->
                value != oldItem.additionalNums[index]
            }
            Payload.ChangeValue(index, newItem.additionalNums[index])
        }

        else -> null
    }
}