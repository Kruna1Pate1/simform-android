package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemSectionHeaderBinding
import com.krunal.demo.databinding.ListItemWorkBinding
import com.krunal.demo.githubclient.data.local.HomeItem
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class HomeAdapter : BaseAdapter<HomeItem>() {

    override fun getLayoutId(viewType: Int): Int = when (HomeItem.Type.values()[viewType]) {
        HomeItem.Type.HEADING -> R.layout.list_item_section_header
        HomeItem.Type.WORK -> R.layout.list_item_work
        HomeItem.Type.FAVORITES -> TODO()
        HomeItem.Type.RECENT -> TODO()
    }

    override fun getItemViewType(position: Int): Int = itemList[position].type.ordinal

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: HomeItem, adapterPosition: Int
    ) {
        when (binding) {
            is ListItemSectionHeaderBinding -> {
                (data as? HomeItem.Heading).let { binding.heading = it }
            }

            is ListItemWorkBinding -> {
                (data as? HomeItem.WorkItem).let { binding.workItem = it }
            }
        }
    }
}