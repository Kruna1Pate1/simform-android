package com.krunal.demo.githubclient.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemProfileDetailBinding
import com.krunal.demo.githubclient.data.local.Profile
import com.krunal.demo.githubclient.data.local.ProfileCard
import com.krunal.demo.githubclient.data.local.ProfileDetail
import com.krunal.demo.githubclient.ui.base.BaseAdapter
import com.krunal.demo.githubclient.ui.view.SpaceItemDecorator
import com.krunal.demo.uicomponents.extentions.dpFormat

class ProfileAdapter: BaseAdapter<ProfileCard>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.list_item_profile_detail

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding,
        data: ProfileCard,
        adapterPosition: Int
    ) {
        if (binding is ListItemProfileDetailBinding) {
            val adapter = ProfileDetailAdapter().apply {
                submitList(data.profileDetail)
            }
            binding.rvProfileDetail.apply {
                this.adapter = adapter
                addItemDecoration(SpaceItemDecorator(16))
            }
        }
    }
}