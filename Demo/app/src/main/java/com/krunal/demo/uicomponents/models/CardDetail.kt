package com.krunal.demo.uicomponents.models

import com.krunal.demo.uicomponents.models.enums.CardType

data class CardDetail(
    val type: CardType, val number: Int, val amount: String = "$0"
) {

    var isSelected: Boolean = false

    constructor(type: CardType, number: Int, amount: String, isSelected: Boolean) : this(
        type,
        number,
        amount
    ) {
        this.isSelected = isSelected
    }
}