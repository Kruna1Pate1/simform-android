package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.ProfileModel
import com.krunal.demo.githubclient.data.remote.model.request.LogoutRequest
import com.krunal.demo.githubclient.data.repository.UserRepository
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.utils.PreferenceKeys
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _profile = MutableStateFlow<ProfileModel?>(null)
    val profile = _profile.asStateFlow()

    fun getUser() {
        viewModelScope.launch {
            userRepository.getAuthorizedUser().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        _profile.emit(resource.data)
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }

    fun logout(onLogout: () -> Unit) {
        viewModelScope.launch {
            val body = LogoutRequest(PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, ""))
            userRepository.logout(AppConstants.GITHUB_CLIENT_ID, body).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        PreferenceHelper.putString(PreferenceKeys.AUTHORIZATION_TOKEN, "")
                        _isLoading.emit(false)
                        onLogout()
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}