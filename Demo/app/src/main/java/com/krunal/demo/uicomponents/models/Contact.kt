package com.krunal.demo.uicomponents.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

data class Contact(
    val name: String, val number: String, @DrawableRes val profile: Int
) {
    companion object {
        val dummyContacts: List<Contact> = buildList {
            repeat(100) {
                add(Contact("Krunal Patel", "+91 9920304837", R.drawable.profile))
                add(Contact("Harsh Mehta", "+91 6023949309", R.drawable.profile))
            }
        }
    }
}
