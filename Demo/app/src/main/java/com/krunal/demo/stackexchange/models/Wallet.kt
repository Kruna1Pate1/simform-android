package com.krunal.demo.stackexchange.models

data class Wallet(
    val amount: String,
    val amountDiff: String,
    val diffPercentage: String,
    val maxValue: String,
    val isPositive: Boolean = true
) {
    companion object {
        val dummyWallet: Wallet
            get() = Wallet(
                "182.057,83",
                "31.800,52",
                "21,1",
                "185.701,18"
            )
    }
}
