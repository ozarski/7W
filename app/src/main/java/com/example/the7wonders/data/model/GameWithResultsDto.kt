package com.example.the7wonders.data.model

data class GameWithResultsDto(
    val gameID: Long,
    val playerID: Long,
    val name: String,
    val date: Long,
    val totalScore: Int
)