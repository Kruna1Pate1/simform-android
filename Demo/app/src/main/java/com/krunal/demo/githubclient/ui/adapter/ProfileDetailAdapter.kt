package com.krunal.demo.githubclient.ui.adapter

import androidx.databinding.ViewDataBinding
import com.krunal.demo.R
import com.krunal.demo.databinding.ListItemProfileInfoBinding
import com.krunal.demo.databinding.ListItemProfileNameBinding
import com.krunal.demo.githubclient.data.local.ProfileDetail
import com.krunal.demo.githubclient.ui.base.BaseAdapter

class ProfileDetailAdapter : BaseAdapter<ProfileDetail>() {

    override fun getLayoutId(viewType: Int): Int = when (DetailType.values()[viewType]) {
        DetailType.NAME -> R.layout.list_item_profile_name
        DetailType.INFO -> R.layout.list_item_profile_info
    }

    override fun getItemViewType(position: Int): Int = itemList[position].type.ordinal

    override fun setDataForListItemWithPosition(
        binding: ViewDataBinding, data: ProfileDetail, adapterPosition: Int
    ) {
        when (data) {
            is ProfileDetail.ProfileName -> {
                (binding as? ListItemProfileNameBinding)?.let { binding ->
                    binding.profileName = data
                }
            }

            is ProfileDetail.ProfileInfo -> {
                (binding as? ListItemProfileInfoBinding)?.let { binding ->
                    binding.profileInfo = data
                }
            }
        }
    }

    enum class DetailType {
        NAME, INFO
    }
}