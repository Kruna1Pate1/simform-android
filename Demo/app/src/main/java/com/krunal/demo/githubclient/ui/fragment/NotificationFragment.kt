package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentNotificationBinding
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.adapter.HomeAdapter
import com.krunal.demo.githubclient.ui.adapter.NotificationAdapter
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.NotificationViewModel
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {

    private lateinit var notificationAdapter: NotificationAdapter
    override val viewModel: NotificationViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_notification

    override fun initialize() {
        super.initialize()
        setupUI()
        setupNotification()
        viewModel.getNotifications()
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.notifications.collectLatest { notification ->
                    notificationAdapter.submitList(notification)
                }
            }
        }
    }

    private fun setupUI() {

    }

    private fun setupNotification() {
        notificationAdapter = NotificationAdapter()
        binding.rvNotification.apply {
            adapter = notificationAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }
}