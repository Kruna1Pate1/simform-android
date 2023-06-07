package com.krunal.demo.searchwebview.ui.viewmodels

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.searchwebview.data.models.PackageDetail
import com.krunal.demo.searchwebview.data.repositories.PackagesRepository
import com.krunal.demo.searchwebview.helpers.SearchCountDownTimer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _suggestions: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val suggestions: StateFlow<List<String>> = _suggestions

    private val _packageDetails: MutableStateFlow<List<PackageDetail>> = MutableStateFlow(emptyList())
    val packageDetails: StateFlow<List<PackageDetail>> = _packageDetails

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var job: Job? = null
    private var countDownTimer: CountDownTimer? = null

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
            _packageDetails.emit(PackagesRepository.getPackageDetails())
            _suggestions.emit(PackagesRepository.getPackageNames())
        }
    }

    fun searchQuery(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(DELAY)
            _query.emit(query)
        }
    }

    fun searchQueryWithCountDown(query: String) {
        countDownTimer?.cancel()
        countDownTimer = SearchCountDownTimer(DELAY) {
            viewModelScope.launch {
                _query.emit(query)
            }
        }.start()
    }

    companion object {
        private const val DELAY = 1500L
    }
}