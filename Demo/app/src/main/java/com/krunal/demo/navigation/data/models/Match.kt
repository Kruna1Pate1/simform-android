package com.krunal.demo.navigation.data.models

data class Match(
    val matchId: Int,
    val user1: UserProfile,
    val user2: UserProfile,
    val winner: UserProfile? = null
)
