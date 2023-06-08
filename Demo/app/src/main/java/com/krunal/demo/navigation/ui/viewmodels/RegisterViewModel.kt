package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.R
import com.krunal.demo.navigation.data.models.Credential
import com.krunal.demo.navigation.data.models.RegisterDetail
import com.krunal.demo.navigation.data.models.UserProfile
import com.krunal.demo.navigation.data.repositories.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    val registerDetail: MutableStateFlow<RegisterDetail> =
        MutableStateFlow(RegisterDetail())

    private val _userProfile: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
            val userDetail = UserProfileRepository.getCurrentUser() ?: return@launch

            registerDetail.emit(RegisterDetail(userDetail.name, userDetail.credential.email, userDetail.credential.password))
            _userProfile.emit(userDetail)
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            registerDetail.value.let { details ->
                val userProfile = UserProfile(
                    1,
                    details.username,
                    Credential(details.email, details.password),
                    R.drawable.avatar_5_raster
                )

                UserProfileRepository.setCurrentUser(userProfile)
                _userProfile.emit(userProfile)
            }
        }
    }
}