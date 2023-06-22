package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemContainerBinding
import com.krunal.demo.databinding.ListItemProfileDetailBinding
import com.krunal.demo.githubclient.data.local.ProfileInfo
import com.krunal.demo.githubclient.data.local.ProfileType
import com.krunal.demo.githubclient.ui.base.BaseAdapter
import com.krunal.demo.githubclient.ui.view.SpaceItemDecorator

class ProfileAdapter : BaseAdapter<ProfileInfo>() {

    override fun getLayoutId(viewType: Int): Int = when (ProfileType.values()[viewType]) {
        ProfileType.DETAIL -> R.layout.list_item_profile_detail
        ProfileType.ITEM -> R.layout.list_item_container
    }

    override fun getItemViewType(position: Int): Int = itemList[position].type.ordinal

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding,
        data: ProfileInfo,
        adapterPosition: Int
    ) {

        when (binding) {
            is ListItemProfileDetailBinding -> {
                val adapter = ProfileDetailAdapter().apply {
                    (data as? ProfileInfo.ProfileCard)?.profileDetail?.let(this::submitList)
                }
                binding.rvProfileDetail.apply {
                    this.adapter = adapter
                    addItemDecoration(SpaceItemDecorator(16))
                }
            }

            is ListItemContainerBinding -> {
                (data as? ProfileInfo.ProfileItem)?.let { binding.profileItem = it }
            }
        }
    }
}