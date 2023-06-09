package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.remote.api.AuthorizationService
import com.krunal.demo.githubclient.data.remote.model.request.AuthorizationRequest
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationErrorResponse
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationResponse
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthorizationRepository(private val gitHubService: AuthorizationService): BaseRepository() {

    fun getAuthorizationToken(body: AuthorizationRequest) =
        flow<Resource<AuthorizationResponse>> {
            emit(Resource.Loading())
            gitHubService.getAuthorizationToken(body).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        }.flowOn(Dispatchers.IO)
}