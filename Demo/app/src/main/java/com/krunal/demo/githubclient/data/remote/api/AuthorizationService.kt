package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.request.AuthorizationRequest
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthorizationService {

    @Headers("Accept: application/json")
    @POST("access_token")
    suspend fun getAuthorizationToken(@Body body: AuthorizationRequest): Response<AuthorizationResponse>
}