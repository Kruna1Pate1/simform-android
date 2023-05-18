package com.krunal.demo.stackexchange.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.stackexchange.models.ShareDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletFragmentViewModel : ViewModel() {

    private val _portfolios: MutableStateFlow<List<ShareDetails>?> = MutableStateFlow(null)
    val portfolios: StateFlow<List<ShareDetails>?> = _portfolios

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _portfolios.emit(
                ShareDetails.dummyData
            )
        }
    }
}