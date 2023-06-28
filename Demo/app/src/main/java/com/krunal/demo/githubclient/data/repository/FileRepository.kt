package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.githubclient.data.local.DownloadState
import com.krunal.demo.githubclient.data.remote.api.FileService
import com.krunal.demo.githubclient.extentions.saveFile
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.githubclient.util.GitHubUrls
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.File

class FileRepository(private val fileService: FileService) : BaseRepository() {

    suspend fun downloadFile(fileUrl: String, destination: File): Flow<DownloadState> =
        fileService.downloadFile(fileUrl).saveFile(destination)

    suspend fun uploadImage(fileName: String, image: ByteArray) = flow<Resource<String>> {
        val requestBody = image.toRequestBody("image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("image", fileName, requestBody)

        fileService.uploadImageFile(GitHubUrls.IMAGE_URL, GitHubUrls.IMGBB_KEY, image)
            .let { response ->
                emit(Resource.Loading())
                val resource = handleResponse(response)
                val data = resource.data?.data?.url
                if (resource is Resource.Success) {
                    emit(Resource.Success(data))
                } else if (resource is Resource.Error) {
                    emit(Resource.Error(resource.message ?: "Image upload fail", data))
                }
            }
    }
}