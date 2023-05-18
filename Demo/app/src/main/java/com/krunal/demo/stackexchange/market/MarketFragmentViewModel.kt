package com.krunal.demo.stackexchange.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.stackexchange.models.ShareDetails
import com.krunal.demo.stackexchange.models.TitleCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarketFragmentViewModel: ViewModel() {

    private val _titleCardModel: MutableStateFlow<TitleCardModel?> = MutableStateFlow(null)
    val titleCardModel: StateFlow<TitleCardModel?> = _titleCardModel

    private val _shareDetails: MutableStateFlow<List<ShareDetails>?> = MutableStateFlow(null)
    val shareDetails: StateFlow<List<ShareDetails>?> = _shareDetails

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _titleCardModel.emit(
                TitleCardModel("BUY", "50.000", "SHIB")
            )

            _shareDetails.emit(ShareDetails.dummyData)
        }
    }

}