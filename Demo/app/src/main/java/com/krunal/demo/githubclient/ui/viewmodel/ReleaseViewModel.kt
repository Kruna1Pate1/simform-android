package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.DownloadState
import com.krunal.demo.githubclient.data.local.Release
import com.krunal.demo.githubclient.data.local.ReleaseAsset
import com.krunal.demo.githubclient.data.repository.RepoRepository
import com.krunal.demo.githubclient.ui.base.BaseViewModel
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ReleaseViewModel @Inject constructor(
    private val repoRepository: RepoRepository
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _release = MutableStateFlow<Release?>(null)
    val release = _release.asStateFlow()

    private val _downloadProgress = MutableStateFlow(0)
    val downloadProgress = _downloadProgress.asStateFlow()

    fun getRepository(repoName: String) {
        viewModelScope.launch {
            repoRepository.getLatestReleaseAssets(repoName).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        resource.data?.let { _release.emit(it) }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }

    fun downloadAsset(asset: ReleaseAsset, destination: File) {

        viewModelScope.launch {
            repoRepository.downloadAsset(asset.downloadUrl, destination).collectLatest { state ->
                when (state) {
                    is DownloadState.Downloading -> {
                        _downloadProgress.emit(state.progress)
                    }

                    DownloadState.Finished -> {
                        _downloadProgress.emit(100)
                    }

                    is DownloadState.Failed -> {

                    }
                }
            }
        }
    }
}