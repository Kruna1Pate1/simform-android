package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.navigation.data.models.UserProfile
import com.krunal.demo.navigation.data.repositories.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {

    private val _userProfile: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    fun setUser(userId: Int) {
        viewModelScope.launch {
            _userProfile.emit(UserProfileRepository.getUserProfiles()
                .firstOrNull { it.id == userId })
        }
    }
}