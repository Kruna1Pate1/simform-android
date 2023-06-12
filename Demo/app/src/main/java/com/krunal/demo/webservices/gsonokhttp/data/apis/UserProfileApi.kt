package com.krunal.demo.webservices.gsonokhttp.data.apis

import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object UserProfileApi {

    private const val USER_URL = "${AppConstants.MOCKAPI_BASE_URL}/users"
    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    private val client = OkHttpClient()

    suspend fun getAllUsers() = flow<Resource<List<UserDetail>>> {
        emit(Resource.Loading())
        val request = Request.Builder().url(USER_URL).get().build()

        try {
            val response = client.newCall(request).execute()
            Resource.Success("")

            if (!response.isSuccessful) {
                emit(Resource.Error(response.message))
            }

            response.body?.string()?.let { body ->
                val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()

                val users = gson.fromJson(body, Array<UserDetail>::class.java).toList()
                emit(Resource.Success(users))
            }

        } catch (e: JsonParseException) {
            emit(Resource.Error(e.message ?: "Can't parse response"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Can't make request"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUser(userId: Int) = flow<Resource<UserDetail>> {
        val request = Request.Builder().url("$USER_URL/$userId").get().build()

        try {
            val response = client.newCall(request).execute()
            Resource.Success("")

            if (!response.isSuccessful) {
                emit(Resource.Error(response.message))
            }

            response.body?.string()?.let { body ->
                val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()

                val user = gson.fromJson(body, UserDetail::class.java)
                emit(Resource.Success(user))
            }

        } catch (e: JsonParseException) {
            emit(Resource.Error(e.message ?: "Can't parse response"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Can't make request"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun registerUser(userDetail: UserDetail) {
        withContext(Dispatchers.IO) {
            val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
            val user = gson.toJson(userDetail, UserDetail::class.java)
            val body = user.toRequestBody("application/json".toMediaType())
            val request = Request.Builder().url(USER_URL).post(body)
                .addHeader("Content-Type", "application/json").build()

            client.newCall(request).execute()
        }
    }

    suspend fun updateUser(userDetail: UserDetail) {
        withContext(Dispatchers.IO) {
            val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
            val user = gson.toJson(userDetail, UserDetail::class.java)
            val body = user.toRequestBody("application/json".toMediaType())
            val request = Request.Builder().url("$USER_URL/${userDetail.userId}")
                .put(body)
                .addHeader("Content-Type", "application/json").build()

            client.newCall(request).execute()
        }
    }
}