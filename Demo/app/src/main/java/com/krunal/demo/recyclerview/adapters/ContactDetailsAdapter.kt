package com.krunal.demo.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.ItemContactDetailBinding
import com.krunal.demo.recyclerview.models.ContactDetail

class ContactDetailsAdapter :
    RecyclerView.Adapter<ContactDetailsAdapter.ContactDetailsViewHolder>() {

    private val contactDetails: MutableList<ContactDetail> = mutableListOf()
    private var currentExpandedPosition: Int = SELECTED_NONE

    class ContactDetailsViewHolder(private val binding: ItemContactDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contactDetail: ContactDetail, onClick: () -> Unit) {
            binding.contactDetail = contactDetail
            binding.root.setOnClickListener {
                onClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactDetailBinding.inflate(layoutInflater)
        return ContactDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int = contactDetails.count()

    override fun onBindViewHolder(holder: ContactDetailsViewHolder, position: Int) {
        contactDetails[position].let { detail ->
            holder.bind(detail) {
                detail.isExpanded = detail.isExpanded.not()
                notifyItemChanged(position)

                when (currentExpandedPosition) {
                    SELECTED_NONE -> currentExpandedPosition = holder.adapterPosition

                    holder.adapterPosition -> currentExpandedPosition = SELECTED_NONE

                    else -> {
                        contactDetails[currentExpandedPosition].isExpanded = false
                        notifyItemChanged(currentExpandedPosition)
                        currentExpandedPosition = holder.adapterPosition
                    }
                }
            }
        }

    }

    fun submitList(list: List<ContactDetail>) {
        contactDetails.clear()
        contactDetails.addAll(list)
        notifyDataSetChanged()
    }

    fun collapseAll() {
        if (currentExpandedPosition != SELECTED_NONE) {
            contactDetails[currentExpandedPosition].isExpanded = false
            notifyItemChanged(currentExpandedPosition)
            currentExpandedPosition = SELECTED_NONE
        }
    }

    companion object {
        const val SELECTED_NONE = -1
    }
}