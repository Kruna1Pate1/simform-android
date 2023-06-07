package com.krunal.demo.uicomponents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.krunal.demo.databinding.ContactLayoutBinding
import com.krunal.demo.uicomponents.models.Contact

class ContactAdapter : BaseAdapter() {

    private val contacts: MutableList<Contact> =
        Contact.dummyContacts.toMutableList()// mutableListOf()

    override fun getCount(): Int = contacts.count()

    override fun getItem(position: Int): Any = contacts[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
//        val binding: ContactLayoutBinding = if (convertView != null) {
//            DataBindingUtil.getBinding(convertView) ?: ContactLayoutBinding.inflate(
//                inflater, parent, false
//            )
//        } else {
//            ContactLayoutBinding.inflate(inflater, parent, false)
//        }
        val binding = ContactLayoutBinding.inflate(inflater, parent, false)
        binding.contact = contacts[position]
        return binding.root
    }

    fun submitList(list: List<Contact>) {
        contacts.clear()
        contacts.addAll(list)
        notifyDataSetChanged()
    }

    // If not using data binding
    // class ViewHolder(val itemView: View)
}