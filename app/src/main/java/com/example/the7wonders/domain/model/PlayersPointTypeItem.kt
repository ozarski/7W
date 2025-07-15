package com.example.the7wonders.domain.model

import com.example.the7wonders.ui.addGameScreen.PointType

data class PlayersPointTypeItem(
    val playerID: Long,
    val playerName: String,
    val pointType: PointType,
    val value: Int
)