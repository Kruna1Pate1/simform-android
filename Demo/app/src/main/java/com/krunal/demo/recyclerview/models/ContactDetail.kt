package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R
import com.krunal.demo.recyclerview.models.enums.Gender

data class ContactDetail(
    val name: String,
    @DrawableRes val profileImage: Int,
    val age: Int,
    val gender: Gender,
    val email: String,
    var isExpanded: Boolean = false
) {

    companion object {
        val dummyData: List<ContactDetail> = buildList {
            repeat(20) {
                add(
                    ContactDetail(
                        "Krunal Patel", R.drawable.profile, 20, Gender.MALE, "krunal@protonmail.com"
                    )
                )
                add(
                    ContactDetail(
                        "Harsh Mehta",
                        R.drawable.thumbnail1,
                        21,
                        Gender.MALE,
                        "harsh@gmail.com"
                    )
                )
                add(
                    ContactDetail(
                        "Ankur Gamit",
                        R.drawable.dark_forest,
                        23,
                        Gender.MALE,
                        "ankur@gmail.com"
                    )
                )
            }
        }
    }
}