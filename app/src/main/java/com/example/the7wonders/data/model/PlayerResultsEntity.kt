package com.example.the7wonders.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["playerID", "gameID"], tableName = "PlayerResults")
data class PlayerResultsEntity(
    val playerID: Long,
    val gameID: Long,
    val wonderPoints: Int,
    val militaryPoints: Int,
    val gold: Int,
    val blueCardPoints: Int,
    val yellowCardPoints: Int,
    val greenCardPoints: Int,
    val purpleCardPoints: Int,
    val totalPoints: Int,
    val placement: Int
)
