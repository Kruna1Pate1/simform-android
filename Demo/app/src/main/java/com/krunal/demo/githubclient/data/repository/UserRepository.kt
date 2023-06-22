package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.local.ProfileInfo
import com.krunal.demo.githubclient.data.local.ProfileModel
import com.krunal.demo.githubclient.data.remote.api.UserService
import com.krunal.demo.githubclient.data.remote.model.response.ApiErrorResponse
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(private val userService: UserService) {

    fun getAuthorizedUser() = flow<Resource<ProfileModel>> {
        emit(Resource.Loading())
        userService.getAuthorizedUser().let { response ->
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val infos: MutableList<ProfileInfo> =
                            ProfileInfo.ProfileItem.from(it).toMutableList()
                        infos.add(0, ProfileInfo.ProfileCard.from(it))
                        emit(Resource.Success(ProfileModel(infos)))
                    }

                } else {
                    if (response.code() in 400..499) {
                        response.errorBody().let {
                            val errorResponse = Gson().fromJson(
                                response.errorBody()?.string(), ApiErrorResponse::class.java
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