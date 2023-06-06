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

    fun loadMatch(matchId: Int) {
        viewModelScope.launch {
            _match.emit(MatchRepository.getMatch(matchId))
        }
    }

    fun win() {
        viewModelScope.launch {
            match.value?.let { match ->
                val userProfile = match.user1.copy(
                    wins = match.user1.wins + 1
                )
                UserProfileRepository.updateUser(userProfile)
                _match.emit(
                    match.copy(user1 = userProfile, winner = match.user1)
                )
            }
        }
    }

    fun loss() {
        viewModelScope.launch {
            match.value?.let { match ->
                val userProfile = match.user2.copy(
                    wins = match.user2.wins + 1
                )
                UserProfileRepository.updateUser(userProfile)
                _match.emit(
                    match.copy(user2 = userProfile, winner = match.user2)
                )
            }
        }
    }
}