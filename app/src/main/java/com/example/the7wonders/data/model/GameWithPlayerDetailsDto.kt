package com.example.the7wonders.data.model

import com.example.the7wonders.domain.model.GameDetailsModel
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.domain.model.PointType

data class GameWithPlayerDetailsDto(
    val playerID: Long,
    val gameID: Long,
    val wonderPoints: Int,
    val militaryPoints: Int,
    val gold: Int,
    val blueCardPoints: Int,
    val yellowCardPoints: Int,
    val greenCardPoints: Int,
    val purpleCardPoints: Int,
    val totalScore: Int,
    val placement: Int,
    val date: Long,
    val name: String,
) {
    companion object {
        fun toGameDetailsModel(scores: List<GameWithPlayerDetailsDto>): GameDetailsModel {
            if (scores.isEmpty()) {
                throw Exception("Score list is empty!")
            }
            return GameDetailsModel(
                id = scores.first().gameID,
                date = scores.first().date,
                playerScores = scores.map { it.toPlayerResultModel() }
            )
        }
    }
}

fun GameWithPlayerDetailsDto.toPlayerResultModel() = PlayerResultModel(
    playerID = playerID,
    playerName = name,
    totalScore = totalScore,
    placement = placement,
    scores = listOf(
        Pair(PointType.Wonder, wonderPoints),
        Pair(PointType.Military, militaryPoints),
        Pair(PointType.Gold, gold),
        Pair(PointType.Blue, blueCardPoints),
        Pair(PointType.Yellow, yellowCardPoints),
        Pair(PointType.Green, greenCardPoints),
        Pair(PointType.Purple, purpleCardPoints)
    )
)

