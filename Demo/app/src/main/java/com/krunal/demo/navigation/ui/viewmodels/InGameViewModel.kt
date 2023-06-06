package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.navigation.data.models.Match
import com.krunal.demo.navigation.data.repositories.MatchRepository
import com.krunal.demo.navigation.data.repositories.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InGameViewModel : ViewModel() {

    private val _match: MutableStateFlow<Match?> = MutableStateFlow(null)
    val match: StateFlow<Match?> = _match

    fun createMatch(userId1: Int, userId2: Int) {
        viewModelScope.launch {
            val user1 = UserProfileRepository.getUserProfile(user)
            val match = Match(
                UserProfileRepository.getAllUserProfiles
            )
            _match.emit(MatchRepository.createMatch())
        }
    }
}