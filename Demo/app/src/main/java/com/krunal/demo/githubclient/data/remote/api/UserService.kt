package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.request.UpdateProfileRequest
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {

    @GET("user")
    suspend fun getAuthorizedUser(): Response<UserResponse>

    @PATCH("user")
    suspend fun updateUser(@Body updateProfileRequest: UpdateProfileRequest): Response<UserResponse>
}