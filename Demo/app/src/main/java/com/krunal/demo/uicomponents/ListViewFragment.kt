package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentListViewBinding
import com.krunal.demo.uicomponents.adapters.ContactAdapter
import com.krunal.demo.uicomponents.models.Contact

class ListViewFragment : Fragment() {

    private lateinit var binding: FragmentListViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = ContactAdapter()
        adapter.submitList(Contact.dummyContacts)
        binding.contactList.adapter = adapter
    }
}