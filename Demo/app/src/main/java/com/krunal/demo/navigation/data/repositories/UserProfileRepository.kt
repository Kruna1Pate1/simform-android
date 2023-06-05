package com.krunal.demo.navigation.data.repositories

import com.krunal.demo.R
import com.krunal.demo.navigation.data.models.Credential
import com.krunal.demo.navigation.data.models.UserProfile

object UserProfileRepository {

    fun getUserProfiles(): List<UserProfile> = listOf(
        UserProfile("Krunal Patel",
            Credential("krunal@gmail.com", "Krunal#321"),
            R.drawable.avatar_1_raster,
            1,
            10,
            5
        ),
        UserProfile("Harsh Mehta",
            Credential("harsh@gmail.com", "Harsh#321"),
            R.drawable.avatar_3_raster,
            2,
            12,
            8
        ),
        UserProfile("Ankur Gamit",
            Credential("ankur@gmail.com", "Ankur@777"),
            R.drawable.avatar_2_raster,
            3,
            4,
            2
        )
    )
}