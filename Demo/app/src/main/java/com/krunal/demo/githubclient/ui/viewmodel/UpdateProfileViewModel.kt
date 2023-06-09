package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.UpdateProfileDetail
import com.krunal.demo.githubclient.data.repository.UserRepository
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val profileDetail = MutableStateFlow<UpdateProfileDetail?>(null)

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUserUpdateDetail().collectLatest { resource ->
                handleUserResponse(resource)
            }
        }
    }

    fun updateDetail() {
        viewModelScope.launch {
            profileDetail.value?.let(userRepository::updateUser)?.collectLatest { resource ->
                handleUserResponse(resource)
            }
        }
    }

    private suspend fun handleUserResponse(resource: Resource<UpdateProfileDetail>) {
        when (resource) {
            is Resource.Loading -> {
                _isLoading.emit(true)
            }

            is Resource.Success -> {
                _isLoading.emit(false)
                resource.data?.let { profileDetail.emit(it) }
            }

            is Resource.Error -> {
                _isLoading.emit(false)
            }
        }
    }
}