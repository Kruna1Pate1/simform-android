package com.krunal.demo.webservices.gsonokhttp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemUserDetailBinding
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail

class UserAdapter(private val onClick: (userDetail: UserDetail) -> Unit) : RecyclerView.Adapter<UserAdapter.UserDetailHolder>() {

    private val newsList: MutableList<UserDetail> = mutableListOf()

    class UserDetailHolder(
        private val binding: ItemUserDetailBinding,
        val onClick: (UserDetail) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userDetail: UserDetail) {
            binding.userDetail = userDetail
            binding.root.setOnClickListener {
                onClick(userDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserDetailBinding.inflate(layoutInflater, parent, false)
        return UserDetailHolder(binding, onClick)
    }

    override fun getItemCount(): Int = newsList.count()

    override fun onBindViewHolder(holder: UserDetailHolder, position: Int) {
        holder.bind(newsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<UserDetail>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}