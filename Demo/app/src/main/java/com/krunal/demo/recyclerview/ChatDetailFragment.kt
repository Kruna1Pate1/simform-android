package com.krunal.demo.recyclerview

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.krunal.demo.databinding.FragmentChatDetailBinding
import com.krunal.demo.recyclerview.adapters.ChatAdapter
import com.krunal.demo.recyclerview.adapters.ProfileImagesAdapter
import com.krunal.demo.recyclerview.decorations.SpaceDecoration
import com.krunal.demo.recyclerview.listeners.OnChatChangeListener
import com.krunal.demo.recyclerview.viewmodels.ChatDetailsViewModel
import com.krunal.demo.uicomponents.extentions.dpFormat
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChatDetailFragment : Fragment(), OnChatChangeListener {

    private lateinit var binding: FragmentChatDetailBinding
    private val viewModel: ChatDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        val profileAdapter = ProfileImagesAdapter().apply {
            onChatChangeListener = this@ChatDetailFragment
        }

        val chatsAdapter = ProfileImagesAdapter().apply {
            shouldTruncate = true
        }

        binding.apply {
            rvProfile.adapter = profileAdapter
            rvProfile.addItemDecoration(SpaceDecoration(SpaceDecoration.HORIZONTAL))

            rvChats.adapter = chatsAdapter
            rvChats.addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
                ) {
                    if (parent.getChildAdapterPosition(view) == 0) return

                    outRect.offset(-(12).dpFormat(requireContext()), 0)
                }
            })
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.chatDetails.collectLatest { list ->
                        profileAdapter.submitList(list.map {
                            it.contactDetail.profileImage
                        })
                    }
                }

                launch {
                    viewModel.currentDetail.collectLatest { detail ->
                        detail?.chats?.let { chats ->
                            chatsAdapter.shouldTruncate = true
                            chatsAdapter.submitList(chats.map { it.profileImage })
                        }
                    }
                }
            }
        }
    }

    override fun onProfileClick(position: Int) {
        viewModel.changeContactDetail(position)
    }
}