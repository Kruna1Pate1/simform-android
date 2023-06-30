package com.krunal.demo.webservices.gsonokhttp.repositories

import com.krunal.demo.webservices.gsonokhttp.data.apis.UserProfileApi
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.flow.Flow

object UserRepository {

    suspend fun getAllUsers(): Flow<Resource<List<UserDetail>>> {
        return UserProfileApi.getAllUsers()
    }

    suspend fun getUser(userId: Int): Flow<Resource<UserDetail>> {
        return UserProfileApi.getUser(userId)
    }

    suspend fun registerUser(userDetail: UserDetail) {
        UserProfileApi.registerUser(userDetail)
    }

    suspend fun updateUser(userDetail: UserDetail) {
        UserProfileApi.updateUser(userDetail)
    }

    suspend fun deleteUser(userId: Int) {
        UserProfileApi.deleteUser(userId)
    }
}