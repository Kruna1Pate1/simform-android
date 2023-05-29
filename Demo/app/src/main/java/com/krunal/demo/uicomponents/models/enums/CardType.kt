package com.krunal.demo.uicomponents.models.enums

import java.util.Locale

enum class CardType {
    DEBIT, CREDIT;

    override fun toString(): String {
        return name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}