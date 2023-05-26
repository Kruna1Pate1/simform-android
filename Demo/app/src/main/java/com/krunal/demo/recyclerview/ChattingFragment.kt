package com.krunal.demo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.FragmentChattingBinding
import com.krunal.demo.recyclerview.adapters.ChatAdapter
import com.krunal.demo.recyclerview.decorations.ChatItemDecoration
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.viewmodels.ChattingFragmentViewModel
import kotlinx.coroutines.launch

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
        val chatAdapter = ChatAdapter()
        chatAdapter.submitList(Message.dummyData)

        binding.rvChat.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
            setHasFixedSize(true)
            addItemDecoration(ChatItemDecoration())
        }

        chatAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                viewModel.removeMessage(positionStart)
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.messages.collect { messages ->
                    chatAdapter.submitList(messages)
                }
            }
        }
    }
}