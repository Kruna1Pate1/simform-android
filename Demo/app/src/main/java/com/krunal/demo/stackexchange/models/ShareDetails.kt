package com.krunal.demo.stackexchange.models

import androidx.annotation.DrawableRes

data class ShareDetails(
    val name: String,
    @DrawableRes val logo: Int,
    val amountDiff: String,
    val isPositive: Boolean = true,
    val value: String,
    val price: String
)
