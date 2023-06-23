package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.response.NotificationsResponseItem
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationService {

    @GET("notifications")
    suspend fun getNotifications(@Query("all") all: Boolean = true): Response<List<NotificationsResponseItem>>
}