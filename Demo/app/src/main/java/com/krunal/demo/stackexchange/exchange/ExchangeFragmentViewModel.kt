package com.krunal.demo.stackexchange.exchange

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.DemoApplication
import com.krunal.demo.R
import com.krunal.demo.stackexchange.models.ExchangeModel
import com.krunal.demo.stackexchange.models.ShareDetails
import com.krunal.demo.stackexchange.models.TitleCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeFragmentViewModel : ViewModel() {

    private val context: Context

    private val _dateTime: MutableStateFlow<String> = MutableStateFlow("")
    val dateTime: StateFlow<String> = _dateTime

    private val _titleCardModel: MutableStateFlow<TitleCardModel?> = MutableStateFlow(null)
    val titleCardModel: StateFlow<TitleCardModel?> = _titleCardModel

    private val _exchangeModel: MutableStateFlow<ExchangeModel?> = MutableStateFlow(null)
    val exchangeModel: StateFlow<ExchangeModel?> = _exchangeModel

    init {
        context = DemoApplication.instance.applicationContext
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _dateTime.emit("OCTOBER 18, TUESDAY 3:19:23 PM")

            _titleCardModel.emit(
                TitleCardModel("EXCHANGE FOR", "1.497,68", "LINK")
            )

            _exchangeModel.emit(
                ExchangeModel(
                    ShareDetails(
                        "Avax",
                        R.drawable.ic_avax_logo,
                        "0,187200",
                        true,
                        "47,38662",
                        "6.022,84"
                    ), ShareDetails(
                        "Link",
                        R.drawable.ic_link_logo,
                        "127,100880",
                        false,
                        "1.497,68",
                        "6.022,84"
                    ),
                    "32,80"
                )
            )
        }
    }
}