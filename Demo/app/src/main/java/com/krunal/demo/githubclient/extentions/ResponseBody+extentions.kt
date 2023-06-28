package com.krunal.demo.githubclient.extentions

import com.krunal.demo.githubclient.data.local.DownloadState
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.File

fun ResponseBody.saveFile(destination: File) = flow<DownloadState> {
    emit(DownloadState.Downloading(0))

    try {
        byteStream().use { inputStream ->
            destination.outputStream().use { outputStream ->
                val totalBytes = contentLength()
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var progressBytes = 0L
                var bytes = inputStream.read(buffer)
                while (bytes >= 0) {
                    outputStream.write(buffer, 0, bytes)
                    progressBytes += bytes
                    bytes = inputStream.read(buffer)
                    emit(DownloadState.Downloading(((progressBytes * 100) / totalBytes).toInt()))
                }
                inputStream.copyTo(outputStream)
            }
        }
    } catch (e: Exception) {
        emit(DownloadState.Failed(e.localizedMessage ?: "download failed", e))
    }
}