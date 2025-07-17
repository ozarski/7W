package com.example.the7wonders.domain.model

import com.example.the7wonders.data.model.PlayerResultEntity
import com.example.the7wonders.ui.addGameScreen.PointType

data class PlayerResultModel(
    val playerID: Long,
    val playerName: String,
    val totalScore: Int,
    val placement: Int,
    val scores: List<Pair<PointType, Int>>
)

fun PlayerResultModel.toPlayerResultEntity(gameID: Long): PlayerResultEntity {
    return PlayerResultEntity(
        playerID = playerID,
        gameID = gameID,
        wonderPoints = scores.firstOrNull { it.first == PointType.Wonder }?.second ?: 0,
        militaryPoints = scores.firstOrNull { it.first == PointType.Military }?.second ?: 0,
        gold = scores.firstOrNull { it.first == PointType.Gold }?.second ?: 0,
        blueCardPoints = scores.firstOrNull { it.first == PointType.Blue }?.second ?: 0,
        yellowCardPoints = scores.firstOrNull { it.first == PointType.Yellow }?.second ?: 0,
        greenCardPoints = scores.firstOrNull { it.first == PointType.Green }?.second ?: 0,
        purpleCardPoints = scores.firstOrNull { it.first == PointType.Purple }?.second ?: 0,
        totalScore = totalScore,
        placement = placement
    )
}