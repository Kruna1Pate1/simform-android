package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.remote.model.response.NotificationsResponseItem
import com.krunal.demo.githubclient.data.repository.NotificationRepository
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _notifications: MutableStateFlow<List<NotificationsResponseItem>> =
        MutableStateFlow(
            emptyList()
        )
    val notifications = _notifications.asStateFlow()

    fun getNotifications() {
        viewModelScope.launch {
            notificationRepository.getNotifications().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        resource.data?.let { _notifications.emit(it) }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}