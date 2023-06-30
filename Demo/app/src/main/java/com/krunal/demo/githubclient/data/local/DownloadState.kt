package com.krunal.demo.githubclient.data.local

sealed interface DownloadState {
    data class Downloading(val progress: Int) : DownloadState
    object Finished : DownloadState
    data class Failed(val message: String, val error: Throwable? = null) : DownloadState
}