package com.krunal.demo.stackexchange.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

data class ShareDetails(
    val name: String,
    val parentName: String,
    @DrawableRes val logo: Int,
    val amountDiff: String,
    val isPositive: Boolean = true,
    val value: String,
    val price: String,
    val percentage: Int,
    val dateTime: String
) {
    companion object {
        val dummyData: List<ShareDetails> = listOf(
            ShareDetails(
                "Avax",
                "AVALANCHE",
                R.drawable.ic_avax_logo,
                "0,187200",
                true,
                "47,38662",
                "6.022,84",
                37,
                "2021-10-19 8:51"
            ), ShareDetails(
                "Rose",
                "OASIS NETWORK",
                R.drawable.ic_rose_logo,
                "0,187200",
                false,
                "47,38662",
                "6.022,84",
                72,
                "2021-10-19 16:13"
            ), ShareDetails(
                "Link",
                "CHAINLINK",
                R.drawable.ic_link_logo,
                "127,10",
                false,
                "50,45872",
                "6.022,84",
                29,
                "2021-10-19 10:00"
            )
        )
    }
}
