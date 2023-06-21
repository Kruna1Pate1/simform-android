package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemProfileDetailBinding
import com.krunal.demo.githubclient.data.local.ProfileDetail
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class ProfileAdapter: BaseAdapter<ProfileDetail>() {

    override fun getLayoutId(): Int = R.layout.list_item_profile_detail

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding,
        data: ProfileDetail,
        adapterPosition: Int
    ) {
        if (binding is ListItemProfileDetailBinding) {
            val adapter = ProfileDetailAdapter().apply {
                submitList(data.profileInfos)
            }
            binding.rvProfileDetail.adapter = adapter
        }
    }
}