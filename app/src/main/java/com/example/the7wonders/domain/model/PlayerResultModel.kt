package com.example.the7wonders.domain.model

import com.example.the7wonders.data.model.PlayerResultEntity

data class PlayerResultModel(
    val playerID: Long,
    val playerName: String,
    val totalScore: Int,
    val placement: Int,
    val scores: List<Pair<PointTypeInterface, Int>>
)

fun PlayerResultModel.toPlayerResultEntity(gameID: Long): PlayerResultEntity {
    return PlayerResultEntity(
        playerID = playerID,
        gameID = gameID,
        wonderPoints = scores.firstOrNull { it.first == BasePointTypes.Wonder }?.second ?: 0,
        militaryPoints = scores.firstOrNull { it.first == BasePointTypes.Military }?.second ?: 0,
        gold = scores.firstOrNull { it.first == BasePointTypes.Gold }?.second ?: 0,
        blueCardPoints = scores.firstOrNull { it.first == BasePointTypes.Blue }?.second ?: 0,
        yellowCardPoints = scores.firstOrNull { it.first == BasePointTypes.Yellow }?.second ?: 0,
        greenCardPoints = scores.firstOrNull { it.first == BasePointTypes.Green }?.second ?: 0,
        purpleCardPoints = scores.firstOrNull { it.first == BasePointTypes.Purple }?.second ?: 0,
        cityCardsPoints = scores.firstOrNull { it.first == CityPointTypes.CityCards }?.second,
        leaderPoinst = scores.firstOrNull { it.first == LeaderPointTypes.LeaderCards }?.second,
        navalConflictsPoints = scores.firstOrNull { it.first == ArmadaPointTypes.NavalConflicts }?.second,
        islandCardsPoints = scores.firstOrNull { it.first == ArmadaPointTypes.IslandCards }?.second,
        totalScore = totalScore,
        placement = placement
    )
}