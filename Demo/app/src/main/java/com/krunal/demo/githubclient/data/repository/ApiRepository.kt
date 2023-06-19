package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.remote.api.ApiService
import com.krunal.demo.githubclient.data.remote.model.response.ApiErrorResponse
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository(private val apiService: ApiService) {

    fun getAuthorizedUser() = flow<Resource<Any>> {
        emit(Resource.Loading())
        apiService.getAuthorizedUser().let { response ->
            try {
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()))
                } else {
                    if (response.code() in 400..499) {
                        response.errorBody().let {
                            val errorResponse = Gson().fromJson(
                                response.errorBody()?.string(),
                                ApiErrorResponse::class.java
                            )
                            emit(Resource.Error(errorResponse.message))
                        }
                    } else {
                        emit(Resource.Error(response.message()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Can't fetch user."))
            }
        }
    }.flowOn(Dispatchers.IO)
}