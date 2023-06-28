package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.R
import com.krunal.demo.githubclient.data.local.NotificationItem
import com.krunal.demo.githubclient.data.remote.api.NotificationService
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NotificationRepository(private val notificationService: NotificationService) :
    BaseRepository() {

    fun getNotifications() = flow<Resource<List<NotificationItem>>> {
        emit(Resource.Loading())
        notificationService.getNotifications().let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.map {
                NotificationItem(
                    R.drawable.ic_git_pull_request_closed,
                    it.repository.fullName,
                    it.subject.url?.split("/")?.last()?.toInt() ?: 0,
                    it.subject.title,
                    it.unread
                )
            }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't fetch notifications", data))
            }
        }
    }.flowOn(Dispatchers.IO)
}