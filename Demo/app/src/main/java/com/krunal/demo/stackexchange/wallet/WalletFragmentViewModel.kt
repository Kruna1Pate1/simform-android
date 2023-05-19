package com.krunal.demo.stackexchange.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.stackexchange.models.ShareDetails
import com.krunal.demo.stackexchange.models.Wallet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletFragmentViewModel : ViewModel() {

    private val _portfolios: MutableStateFlow<List<ShareDetails>?> = MutableStateFlow(null)
    val portfolios: StateFlow<List<ShareDetails>?> = _portfolios

    private val _wallet: MutableStateFlow<Wallet?> = MutableStateFlow(null)
    val wallet: StateFlow<Wallet?> = _wallet

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _portfolios.emit(
                ShareDetails.dummyData
            )

            _wallet.emit(
                Wallet.dummyWallet
            )
        }
    }
}