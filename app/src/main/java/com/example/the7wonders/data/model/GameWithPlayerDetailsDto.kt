package com.example.the7wonders.data.model

import com.example.the7wonders.domain.model.ArmadaPointTypes
import com.example.the7wonders.domain.model.GameDetailsModel
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.domain.model.BasePointTypes
import com.example.the7wonders.domain.model.CityPointTypes
import com.example.the7wonders.domain.model.LeaderPointTypes

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
    val cityCardsPoints: Int?,
    val leaderPoints: Int?,
    val navalConflictsPoints: Int?,
    val islandCardsPoints: Int?,
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
        Pair(BasePointTypes.Wonder, wonderPoints),
        Pair(BasePointTypes.Military, militaryPoints),
        Pair(BasePointTypes.Gold, gold),
        Pair(BasePointTypes.Blue, blueCardPoints),
        Pair(BasePointTypes.Yellow, yellowCardPoints),
        Pair(BasePointTypes.Green, greenCardPoints),
        Pair(BasePointTypes.Purple, purpleCardPoints),
        Pair(CityPointTypes.CityCards, cityCardsPoints),
        Pair(LeaderPointTypes.LeaderCards, leaderPoints),
        Pair(ArmadaPointTypes.NavalConflicts, navalConflictsPoints),
        Pair(ArmadaPointTypes.IslandCards, islandCardsPoints)
    )
)

