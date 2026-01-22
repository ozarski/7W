package com.example.the7wonders.data.model

import androidx.room.Entity
import com.example.the7wonders.data.datasource.DatabaseConstants

@Entity(
    primaryKeys = ["playerID", "gameID"],
    tableName = DatabaseConstants.PLAYER_RESULTS_TABLE_NAME
)
data class PlayerResultEntity(
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
    val navalVictoryPoints: Int?,
    val totalScore: Int,
    val placement: Int
)
