package com.krunal.demo.uicomponents.cardscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.uicomponents.models.CardDetail
import com.krunal.demo.uicomponents.models.enums.CardType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class CardFragmentViewModel : ViewModel() {

    private val _selectedCards = MutableSharedFlow<List<CardDetail>>()
    val selectedCards: SharedFlow<List<CardDetail>> = _selectedCards

    var dummyCardDetails: List<CardDetail> = buildList {
        add(CardDetail(CardType.DEBIT, 1963, "$2,983.78", true))
        add(CardDetail(CardType.DEBIT, 1822, "$1,002.02"))
        add(CardDetail(CardType.CREDIT, 2291, "$540.00"))
    }

    fun updateCardSelection(card: CardDetail, isSelected: Boolean) {
       dummyCardDetails.find { it.number == card.number }?.isSelected =
            isSelected
        viewModelScope.launch {
            _selectedCards.emit(dummyCardDetails)
        }
    }
}