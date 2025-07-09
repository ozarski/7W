package com.example.the7wonders.domain.model

data class GameItem(
    val id: Long,
    val date: Long,
    val playerScores: List<Pair<PlayerItem, Int>>
)