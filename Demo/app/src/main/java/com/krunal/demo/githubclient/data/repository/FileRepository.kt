package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.githubclient.data.local.DownloadState
import com.krunal.demo.githubclient.data.remote.api.FileService
import com.krunal.demo.githubclient.extentions.saveFile
import kotlinx.coroutines.flow.Flow
import java.io.File

class FileRepository(private val fileService: FileService) {

    suspend fun downloadFile(fileUrl: String, destination: File): Flow<DownloadState> =
        fileService.downloadFile(fileUrl).saveFile(destination)
}