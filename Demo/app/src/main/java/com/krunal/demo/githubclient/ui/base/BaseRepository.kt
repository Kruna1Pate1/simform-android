package com.krunal.demo.githubclient.ui.base

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.remote.model.response.ApiError
import com.krunal.demo.webservices.utils.Resource
import retrofit2.Response

abstract class BaseRepository {

    fun <T> handleResponse(response: Response<T>): Resource<T> {
        return try {
            if (response.isSuccessful) {
                return Resource.Success(response.body())
            } else {
                if (response.code() in 400..499) {
                    response.errorBody().let {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(), ApiError::class.java
                        )
                        return Resource.Error(errorResponse.message)
                    }
                } else {
                    return Resource.Error(response.message())
                }
            }
        } catch (e: Error) {
            Resource.Error(e.message ?: "Something went wrong!")
        }
    }
}