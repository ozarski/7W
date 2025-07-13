package com.example.the7wonders.domain.model

data class PlayerItem(
    val id: Long,
    val name: String,
    val wins: Int,
    val games: Int,
    val topScore: Int,
    val avgPlacement: Double
)
