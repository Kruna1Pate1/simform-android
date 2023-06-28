package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.response.ImageUploadResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface FileService {

    @Streaming
    @GET
    suspend fun downloadFile(@Url url: String): ResponseBody

    @Multipart
    @POST("")
    suspend fun uploadImageFile(
        @Url url: String,
        @Query("key") key: String,
        @Part image: MultipartBody.Part
    ): Response<ImageUploadResponse>
}