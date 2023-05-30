package com.krunal.demo.recyclerview.listeners

import androidx.recyclerview.widget.DiffUtil
import com.krunal.demo.recyclerview.models.Calculation

object CalculationDiffCallback : DiffUtil.ItemCallback<Calculation>() {

    override fun areItemsTheSame(oldItem: Calculation, newItem: Calculation): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Calculation, newItem: Calculation): Boolean =
        oldItem == newItem
}