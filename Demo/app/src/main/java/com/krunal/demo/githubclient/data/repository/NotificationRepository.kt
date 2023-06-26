package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.R
import com.krunal.demo.githubclient.data.local.NotificationItem
import com.krunal.demo.githubclient.data.remote.api.NotificationService
import com.krunal.demo.githubclient.data.remote.model.response.ApiError
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NotificationRepository(private val notificationService: NotificationService) {

    fun getNotifications() = flow<Resource<List<NotificationItem>>> {
        emit(Resource.Loading())
        notificationService.getNotifications().let { response ->
            try {
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        val notificationItems = list.map {
                            NotificationItem(
                                R.drawable.ic_git_pull_request_closed,
                                it.repository.fullName,
                                it.subject.url?.split("/")?.last()?.toInt() ?: 0,
                                it.subject.title,
                                it.unread
                            )
                        }
                        emit(Resource.Success(notificationItems))
                    }

                } else {
                    if (response.code() in 400..499) {
                        response.errorBody().let {
                            val errorResponse = Gson().fromJson(
                                response.errorBody()?.string(), ApiError::class.java
                            )
                            emit(Resource.Error(errorResponse.message))
                        }
                    } else {
                        emit(Resource.Error(response.message()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Can't fetch notifications."))
            }
        }
    }.flowOn(Dispatchers.IO)
}