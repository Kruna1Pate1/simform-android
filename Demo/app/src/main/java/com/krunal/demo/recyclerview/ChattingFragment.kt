package com.krunal.demo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentChattingBinding
import com.krunal.demo.recyclerview.adapters.ChatAdapter
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.models.MessageType
import com.krunal.demo.recyclerview.viewmodels.ChattingFragmentViewModel

class ChattingFragment : Fragment() {

    private lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChattingBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvChat.adapter = ChatAdapter().apply {
            submitList(Message.dummyData)
        }

        binding.rvChat.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
        }
    }
}