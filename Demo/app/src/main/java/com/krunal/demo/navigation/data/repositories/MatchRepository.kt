package com.krunal.demo.navigation.data.repositories

import com.krunal.demo.navigation.data.models.Match

object MatchRepository {

    fun getMatch(): Match = Match(
        0,
        UserProfileRepository.getUserProfiles()[0],
        UserProfileRepository.getUserProfiles()[1]
    )
}