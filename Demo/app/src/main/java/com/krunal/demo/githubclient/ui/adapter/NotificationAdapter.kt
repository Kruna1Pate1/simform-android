package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemNotificationBinding
import com.krunal.demo.databinding.ListItemSectionHeaderBinding
import com.krunal.demo.databinding.ListItemWorkBinding
import com.krunal.demo.githubclient.data.local.HomeItem
import com.krunal.demo.githubclient.data.local.NotificationItem
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class NotificationAdapter : BaseAdapter<NotificationItem>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.list_item_notification

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: NotificationItem, adapterPosition: Int
    ) {
        (binding as? ListItemNotificationBinding)?.notificationItem = data
    }
}