package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemRepoContainerBinding
import com.krunal.demo.databinding.ListItemSectionHeaderBinding
import com.krunal.demo.databinding.ListItemWorkBinding
import com.krunal.demo.githubclient.data.local.HomeItem
import com.krunal.demo.githubclient.data.local.RepoDetail
import com.krunal.demo.githubclient.data.local.RepoDetailItem
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class RepoDetailAdapter : BaseAdapter<RepoDetailItem>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.list_item_repo_container

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: RepoDetailItem, adapterPosition: Int
    ) {
        (binding as? ListItemRepoContainerBinding)?.repoItem = data
    }
}