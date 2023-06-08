package com.krunal.demo.navigation.data.repositories

import com.krunal.demo.navigation.data.models.Match

object MatchRepository {

    private val matches = mutableMapOf(
        1 to Match(
            0,
            UserProfileRepository.getAllUserProfiles()[0],
            UserProfileRepository.getAllUserProfiles()[1]
        ),
        2 to Match(
            0,
            UserProfileRepository.getAllUserProfiles()[2],
            UserProfileRepository.getAllUserProfiles()[3]
        )
    )

    fun getMatch(matchId: Int): Match? = matches[matchId]

    fun getAllMatches(): List<Match> = matches.toSortedMap().values.toList()

    fun createMatch(match: Match) {
        matches[match.matchId] = match
    }
}