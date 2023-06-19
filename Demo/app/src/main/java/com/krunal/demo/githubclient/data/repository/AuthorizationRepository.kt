package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.remote.api.AuthorizationService
import com.krunal.demo.githubclient.data.remote.model.request.AuthorizationRequest
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationErrorResponse
import com.krunal.demo.githubclient.data.remote.model.response.AuthorizationResponse
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthorizationRepository(private val gitHubService: AuthorizationService) {

    fun getAuthorizationToken(body: AuthorizationRequest) =
        flow<Resource<AuthorizationResponse>> {
            emit(Resource.Loading())
            gitHubService.getAuthorizationToken(body).let { response ->
                try {
                    if (response.isSuccessful) {
                        emit(Resource.Success(response.body()))
                    } else {
                        if (response.code() in 400..499 && response.code() != 401) {
                            response.errorBody().let {
                                val errorResponse = Gson().fromJson(response.errorBody()?.string(), AuthorizationErrorResponse::class.java)
                                emit(Resource.Error(errorResponse.errorDescription))
                            }
                        } else {
                            emit(Resource.Error(response.message()))
                        }
                    }
                } catch (e: Error) {
                    emit(Resource.Error(e.message ?: "Something went wrong!"))
                }
            }
        }.flowOn(Dispatchers.IO)
}