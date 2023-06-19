package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationResponse
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizedUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("user")
    suspend fun getAuthorizedUser(): Response<AuthorizedUserResponse>
}