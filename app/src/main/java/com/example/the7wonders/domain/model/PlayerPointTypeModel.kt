package com.example.the7wonders.domain.model

data class PlayerPointTypeModel(
    val playerID: Long,
    val playerName: String,
    val pointType: PointType,
    val value: String
)