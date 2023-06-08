package com.krunal.demo.navigation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemLeaderboardUserBinding
import com.krunal.demo.navigation.data.models.UserProfile

class LeaderboardAdapter(private val onClick: (userId: Int) -> Unit) :
    RecyclerView.Adapter<LeaderboardAdapter.UserProfileViewHolder>() {

    private val userProfiles: MutableList<UserProfile> = mutableListOf()

    class UserProfileViewHolder(
        private val binding: ItemLeaderboardUserBinding,
        private val onClick: (userId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userProfile: UserProfile) {
            binding.userProfile = userProfile
            binding.root.setOnClickListener {
                onClick(userProfile.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLeaderboardUserBinding.inflate(layoutInflater, parent, false)
        return UserProfileViewHolder(binding, onClick)
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