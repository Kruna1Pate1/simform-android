package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.navigation.data.models.Match
import com.krunal.demo.navigation.data.models.UserProfile
import com.krunal.demo.navigation.data.repositories.MatchRepository
import com.krunal.demo.navigation.data.repositories.UserProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {

    private val _userProfile1: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    val userProfile1: StateFlow<UserProfile?> = _userProfile1

    private val _userProfile2: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    val userProfile2: StateFlow<UserProfile?> = _userProfile2

    private val _match: MutableStateFlow<Match?> = MutableStateFlow(null)
    val match: StateFlow<Match?> = _match

    fun setUser(userId: Int) {
        viewModelScope.launch {
            val user = if (userId == -1) {
                UserProfileRepository.getCurrentUser()
            } else {
                UserProfileRepository.getUserProfile(userId)
            }
            _userProfile1.emit(user)
        }
    }

    fun createMatch() {

        viewModelScope.launch {
            delay(1200)

            // Insufficient players
            if (UserProfileRepository.getAllUserProfiles().count() <= 1) return@launch

            val user1 = userProfile1.value ?: return@launch
            var user2: UserProfile
            do {
                user2 = UserProfileRepository.getRandomUser() ?: return@launch
            } while (user2.id == user1.id)

            _userProfile2.emit(user2)

            val id = MatchRepository.getAllMatches().count()
            val match = Match(id, user1, user2)

            MatchRepository.createMatch(match)
            _match.emit(match)
        }
    }
}