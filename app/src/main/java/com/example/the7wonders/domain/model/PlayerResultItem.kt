package com.example.the7wonders.domain.model

import com.example.the7wonders.ui.addGameScreen.PointType

data class PlayerResultItem(
    val playerID: Long,
    val playerName: String,
    val totalScore: Int,
    val placement: Int,
    val scores: List<Pair<PointType, Int>>
)
