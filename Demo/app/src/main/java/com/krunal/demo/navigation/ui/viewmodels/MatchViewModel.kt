package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.navigation.data.models.Match
import com.krunal.demo.navigation.data.repositories.MatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {

    private val _match: MutableStateFlow<Match?> = MutableStateFlow(null)
    val match: StateFlow<Match?> = _match

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
            _match.emit(MatchRepository.getMatch())
        }
    }
}