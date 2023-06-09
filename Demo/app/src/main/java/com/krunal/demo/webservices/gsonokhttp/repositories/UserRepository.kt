package com.krunal.demo.webservices.gsonokhttp.repositories

import com.krunal.demo.webservices.gsonokhttp.data.apis.UserProfileApi
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.flow.Flow

object UserRepository {

    suspend fun getAllUsers(): Flow<Resource<List<UserDetail>>> {
        return UserProfileApi.getAllUsers()
    }

    suspend fun registerUser(userDetail: UserDetail) {
        UserProfileApi.registerUser(userDetail)
    }
}