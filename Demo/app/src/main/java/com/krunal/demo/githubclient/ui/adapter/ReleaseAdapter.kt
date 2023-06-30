package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemProfileInfoBinding
import com.krunal.demo.databinding.ListItemProfileNameBinding
import com.krunal.demo.databinding.ListItemReleaseAssetsBinding
import com.krunal.demo.githubclient.data.local.ReleaseAsset
import com.krunal.demo.githubclient.data.local.ProfileDetail
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class ReleaseAdapter : BaseAdapter<ReleaseAsset>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.list_item_release_assets

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: ReleaseAsset, adapterPosition: Int
    ) {
        (binding as? ListItemReleaseAssetsBinding)?.asset = data
    }
}