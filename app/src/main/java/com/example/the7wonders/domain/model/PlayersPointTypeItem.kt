package com.example.the7wonders.domain.model

import com.example.the7wonders.ui.addGameScreen.PointTypes

data class PlayersPointTypeItem(
    val playerID: Long,
    val playerName: String,
    val pointType: PointTypes,
    val value: Int
)