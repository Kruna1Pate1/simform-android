package com.krunal.demo.recyclerview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.databinding.FragmentContactDetailBinding
import com.krunal.demo.recyclerview.adapters.ContactDetailsAdapter
import com.krunal.demo.recyclerview.decorations.ContactDecoration
import com.krunal.demo.recyclerview.viewmodels.ContactDetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactDetailFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailBinding
    private val viewModel: ContactDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupContactDetails()
    }

    private fun setupContactDetails() {
        val adapter = ContactDetailsAdapter()
        binding.rvContactDetails.apply {
            this.adapter = adapter
            addItemDecoration(ContactDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY - oldScrollY > SCROLL_THRESHOLD) {
                    adapter.collapseAll()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.contactDetail.collectLatest { list ->
                    adapter.submitList(list)
                }
            }
        }
    }

    companion object {
        const val SCROLL_THRESHOLD = 10
    }
}