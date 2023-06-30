package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class GitHubClientViewModel @Inject constructor(
    private val apiRepository: UserRepository,

) : ViewModel() {

    private var token: String = ""
    private val _subtitle = MutableStateFlow("")
    val subtitle = _subtitle.asStateFlow()

    fun setToken(token: String) {
        this.token = token
    }

    fun setSubtitle(subtitle: String) {
        viewModelScope.launch {
            _subtitle.emit(subtitle)
        }
    }
}