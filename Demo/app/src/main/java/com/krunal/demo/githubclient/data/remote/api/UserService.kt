package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.request.LogoutRequest
import com.krunal.demo.githubclient.data.remote.model.request.UpdateProfileRequest
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserService {

    @GET("user")
    suspend fun getAuthorizedUser(): Response<UserResponse>

    @PATCH("user")
    suspend fun updateUser(@Body updateProfileRequest: UpdateProfileRequest): Response<UserResponse>

    @HTTP(
        method = "DELETE",
        path = "applications/{clientId}/token",
        hasBody = true
    )
    suspend fun logout(@Path("clientId") clientId: String, @Body body: LogoutRequest): Response<Unit>
}