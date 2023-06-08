package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.navigation.data.models.UserProfile
import com.krunal.demo.navigation.data.repositories.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel : ViewModel() {

    private val _userProfiles: MutableStateFlow<List<UserProfile>> = MutableStateFlow(emptyList())
    val userProfiles: StateFlow<List<UserProfile>> = _userProfiles

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
            _userProfiles.emit(UserProfileRepository.getAllUserProfiles().sortedBy { it.rank })
        }
    }
}