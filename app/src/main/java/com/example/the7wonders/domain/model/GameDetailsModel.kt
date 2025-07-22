package com.example.the7wonders.domain.model

data class GameDetailsModel(
    val id: Long? = null,
    val date: Long? = null,
    val playerScores: List<PlayerResultModel>
)