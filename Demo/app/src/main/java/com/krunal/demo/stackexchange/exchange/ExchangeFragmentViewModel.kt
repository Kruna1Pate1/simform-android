package com.krunal.demo.stackexchange.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.stackexchange.models.ExchangeModel
import com.krunal.demo.stackexchange.models.ShareDetails
import com.krunal.demo.stackexchange.models.TitleCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExchangeFragmentViewModel : ViewModel() {

    private val _dateTime: MutableStateFlow<String> = MutableStateFlow("")
    val dateTime: StateFlow<String> = _dateTime

    private val _titleCardModel: MutableStateFlow<TitleCardModel?> = MutableStateFlow(null)
    val titleCardModel: StateFlow<TitleCardModel?> = _titleCardModel

    private val _exchangeModel: MutableStateFlow<ExchangeModel?> = MutableStateFlow(null)
    val exchangeModel: StateFlow<ExchangeModel?> = _exchangeModel

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _dateTime.emit("OCTOBER 18, TUESDAY 3:19:23 PM")

            _exchangeModel.emit(
                ExchangeModel(
                    ShareDetails.dummyData[0], ShareDetails.dummyData[2], 32.80F
                )
            )

            exchangeModel.collectLatest {
                it?.receive?.let { share ->
                    _titleCardModel.emit(
                        TitleCardModel("EXCHANGE FOR", share.value, share.name)
                    )
                }
            }
        }
    }

    fun changeExchangeAmount(number: Float) {
        viewModelScope.launch {
            exchangeModel.value?.exchange?.let { exchange ->
                _exchangeModel.emit(
                    exchangeModel.value?.copy(
                        exchange = exchange.copy(
                            value = exchange.value + number
                        )
                    )
                )
            }
        }
    }

    fun changeReceiveAmount(number: Float) {
        viewModelScope.launch {
            exchangeModel.value?.receive?.let { receive ->
                _exchangeModel.emit(
                    exchangeModel.value?.copy(
                        receive = receive.copy(
                            value = receive.value + number
                        )
                    )
                )
            }
        }
    }
}