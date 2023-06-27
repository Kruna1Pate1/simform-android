package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.NotificationItem
import com.krunal.demo.githubclient.data.repository.IssueRepository
import com.krunal.demo.githubclient.data.repository.NotificationRepository
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val issueRepository: IssueRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _notifications: MutableStateFlow<List<NotificationItem>> =
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
                        resource.data?.let { notificationItems ->
                            _notifications.emit(notificationItems)
                            getMetadataList(notificationItems)
                        }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }

    private suspend fun getMetadataList(notificationItems: List<NotificationItem>) {
        val notifications = notificationItems.map { it.copy() }
        supervisorScope {
            buildList<Job> {
                notifications.forEach {
                    add(launch { getMetadata(it) })
                }
            }.joinAll()
            _notifications.emit(notifications)
        }
    }

    private suspend fun getMetadata(notificationItem: NotificationItem) {
        if (notificationItem.issueId == null) return

        issueRepository.getIssue(notificationItem.repoName, notificationItem.issueId)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        resource.data?.let {
                            notificationItem.authorAvatar = it.user.avatarUrl
                            notificationItem.description = it.body
                        }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
    }
}