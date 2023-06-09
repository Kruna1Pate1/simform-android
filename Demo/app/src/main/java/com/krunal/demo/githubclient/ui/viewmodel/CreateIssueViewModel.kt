package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.IssueModel
import com.krunal.demo.githubclient.data.remote.model.response.IssueResponse
import com.krunal.demo.githubclient.data.repository.FileRepository
import com.krunal.demo.githubclient.data.repository.IssueRepository
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateIssueViewModel @Inject constructor(
    private val issueRepository: IssueRepository, private val fileRepository: FileRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _issue = MutableStateFlow<IssueResponse?>(null)
    val issue = _issue.asStateFlow()

    private val _issueModel = MutableStateFlow(IssueModel(""))
    val issueModel = _issueModel.asStateFlow()

    private val _imageProgress = MutableStateFlow(0)
    val imageProgress = _imageProgress.asStateFlow()

    var repoName: String = ""

    fun createIssue() {
        if (issueModel.value.title.isEmpty()) return

        viewModelScope.launch {
            issueRepository.createIssue(repoName, issueModel.value).collectLatest { resource ->

            }
        }
    }

    fun addImage(image: ByteArray) {
        viewModelScope.launch {
            val response = fileRepository.uploadImage("image.jpeg", image) { progress ->
                launch {
                    _imageProgress.emit(progress)
                }
            }

            response.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        resource.data?.let { url ->
                            val urlMarkdown = "[$url]($url)"
                            _issueModel.value.body = (_issueModel.value.body ?: "") + urlMarkdown
                        }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}