package com.example.the7wonders.domain.model

data class PlayerPointTypeModel(
    val playerID: Long,
    val playerName: String,
    val pointType: PointTypeInterface,
    val value: String
)