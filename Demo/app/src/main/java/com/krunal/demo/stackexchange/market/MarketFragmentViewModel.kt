package com.krunal.demo.stackexchange.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.stackexchange.models.ShareDetails
import com.krunal.demo.stackexchange.models.TitleCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarketFragmentViewModel : ViewModel() {

    private val _titleCardModel: MutableStateFlow<TitleCardModel?> = MutableStateFlow(null)
    val titleCardModel: StateFlow<TitleCardModel?> = _titleCardModel

    private val _shareDetails: MutableStateFlow<List<ShareDetails>?> = MutableStateFlow(null)
    val shareDetails: StateFlow<List<ShareDetails>?> = _shareDetails

    private val _yourPrice: MutableStateFlow<Float> = MutableStateFlow(0F)
    val yourPrice: StateFlow<Float> = _yourPrice

    private val _amount: MutableStateFlow<Float> = MutableStateFlow(0F)
    val amount: StateFlow<Float> = _amount


    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _titleCardModel.emit(
                TitleCardModel("BUY", 50000F, "SHIB")
            )

            _shareDetails.emit(ShareDetails.dummyData)

            _yourPrice.emit(0.268800F)
            _amount.emit(50000F)
        }
    }

    fun changeYourPriceValue(increase: Boolean = false) {
        viewModelScope.launch {
            _yourPrice.emit(
                if (increase) {
                    yourPrice.value + 0.1F
                } else {
                    yourPrice.value - 0.1F
                }
            )
        }
    }

    fun changeAmountValue(increase: Boolean = false) {
        viewModelScope.launch {
            _amount.emit(
                if (increase) {
                    amount.value + 1
                } else {
                    amount.value - 1
                }
            )
        }
    }

}