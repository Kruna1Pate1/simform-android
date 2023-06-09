package com.krunal.demo.githubclient.data.repository

import com.google.gson.Gson
import com.krunal.demo.githubclient.data.local.ProfileInfo
import com.krunal.demo.githubclient.data.local.ProfileModel
import com.krunal.demo.githubclient.data.local.UpdateProfileDetail
import com.krunal.demo.githubclient.data.remote.api.UserService
import com.krunal.demo.githubclient.data.remote.model.request.LogoutRequest
import com.krunal.demo.githubclient.data.remote.model.request.UpdateProfileRequest
import com.krunal.demo.githubclient.data.remote.model.response.ApiError
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body

class UserRepository(private val userService: UserService): BaseRepository() {

    fun getAuthorizedUser() = flow<Resource<ProfileModel>> {
        emit(Resource.Loading())
        userService.getAuthorizedUser().let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.let {
                val infos: MutableList<ProfileInfo> =
                    ProfileInfo.ProfileItem.from(it).toMutableList()
                infos.add(0, ProfileInfo.ProfileCard.from(it))
                ProfileModel(infos)
            }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't get profile details", data))
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getUserUpdateDetail() = flow<Resource<UpdateProfileDetail>> {
        emit(Resource.Loading())
        userService.getAuthorizedUser().let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.let { UpdateProfileDetail.from(it) }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't get profile details", data))
            }
        }
    }.flowOn(Dispatchers.IO)

    fun updateUser(updateProfileDetail: UpdateProfileDetail) = flow<Resource<UpdateProfileDetail>> {
        emit(Resource.Loading())
        val updateProfileRequest = with(updateProfileDetail) {
            UpdateProfileRequest(
                name, email, website, twitter, company, null, null, bio
            )
        }

        userService.updateUser(updateProfileRequest).let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.let { UpdateProfileDetail.from(it) }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't get profile updates", data))
            }
        }
    }.flowOn(Dispatchers.IO)

    fun logout(clientId: String, body: LogoutRequest) = flow<Resource<Unit>> {
        emit(Resource.Loading())
        userService.logout(clientId, body).let { response ->
            val resource = handleResponse(response)
            emit(resource)
        }
    }
}