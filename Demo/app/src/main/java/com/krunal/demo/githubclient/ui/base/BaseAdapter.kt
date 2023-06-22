package com.krunal.demo.githubclient.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    internal val itemList = mutableListOf<T>()

    @LayoutRes
    abstract fun getLayoutId(viewType: Int = 0): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, getLayoutId(viewType), parent, false)
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.count()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<T>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    open fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: T, adapterPosition: Int
    ) {

    }

    open inner class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T) {
            setDataForListItemWithPosition(binding, data, adapterPosition)
        }
    }
}