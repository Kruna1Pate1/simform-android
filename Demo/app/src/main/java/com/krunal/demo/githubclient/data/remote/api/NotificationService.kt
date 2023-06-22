package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.response.NotificationsResponseItem
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface NotificationService {

    @GET("notifications")
    suspend fun getNotifications(): Response<List<NotificationsResponseItem>>
}