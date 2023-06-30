package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemRepositoryBinding
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class RepoAdapter() : BaseAdapter<RepoCard>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.list_item_repository

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: RepoCard, adapterPosition: Int
    ) {
        (binding as? ListItemRepositoryBinding)?.repoCard = data
    }
}