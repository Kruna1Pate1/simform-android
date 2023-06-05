package com.krunal.demo.navigation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemLeaderboardUserBinding
import com.krunal.demo.navigation.data.models.UserProfile

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.UserProfileViewHolder>() {

    private val userProfiles: MutableList<UserProfile> = mutableListOf()

    class UserProfileViewHolder(val binding: ItemLeaderboardUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userProfile: UserProfile) {
            binding.userProfile = userProfile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLeaderboardUserBinding.inflate(layoutInflater, parent, false)
        return UserProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = userProfiles.count()

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind(userProfiles[position])
    }

    fun submitList(list: List<UserProfile>) {
        userProfiles.clear()
        userProfiles.addAll(list)
        notifyDataSetChanged()
    }
}