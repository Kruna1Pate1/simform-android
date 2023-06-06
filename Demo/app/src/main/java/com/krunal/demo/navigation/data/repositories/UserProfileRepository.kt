package com.krunal.demo.navigation.data.repositories

import com.krunal.demo.R
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.navigation.data.models.Credential
import com.krunal.demo.navigation.data.models.UserProfile
import com.krunal.demo.utils.PreferenceKeys

object UserProfileRepository {

    private val userProfiles = mutableMapOf(
        1 to UserProfile(
            id = 1,
            name = "Krunal Patel",
            credential = Credential("krunal@gmail.com", "Krunal#321"),
            profileImage = R.drawable.avatar_1_raster,
            rank = 1,
            wins = 10,
            losses = 5
        ), 2 to UserProfile(
            id = 2,
            name = "Harsh Mehta",
            credential = Credential("harsh@gmail.com", "Harsh#321"),
            profileImage = R.drawable.avatar_3_raster,
            rank = 2,
            wins = 12,
            losses = 8
        ), 3 to UserProfile(
            id = 3,
            name = "Ankur Gamit",
            credential = Credential("ankur@gmail.com", "Ankur@777"),
            profileImage = R.drawable.avatar_2_raster,
            rank = 3,
            wins = 4,
            losses = 2
        )
    )

    fun getAllUserProfiles(): List<UserProfile> = userProfiles.toSortedMap().values.toList()

    fun getCurrentUser(): UserProfile? {
        val userId = PreferenceHelper.getInt(PreferenceKeys.TRIVIA_USER_ID, -1)
        if (userId == -1) return null

        return userProfiles[userId]
    }

    fun getUserProfile(userId: Int): UserProfile? = userProfiles[userId]

    fun setCurrentUser(userProfile: UserProfile) {
        userProfiles[userProfile.id] = userProfile
        PreferenceHelper.putInt(PreferenceKeys.TRIVIA_USER_ID, userProfile.id)
    }
}