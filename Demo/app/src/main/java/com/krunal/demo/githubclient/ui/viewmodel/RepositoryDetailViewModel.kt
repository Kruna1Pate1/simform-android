package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.data.remote.model.response.Repository
import com.krunal.demo.githubclient.data.repository.RepoRepository
import com.krunal.demo.githubclient.ui.base.BaseViewModel
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private val repoRepository: RepoRepository
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _repository = MutableStateFlow<Repository?>(null)
    val repository = _repository.asStateFlow()

    fun getRepository(repoName: String) {
        viewModelScope.launch {
            repoRepository.getAuthorizedUserRepos().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
//                        resource.data?.let { _re.emit(it) }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}