package com.example.the7wonders.data.model

import androidx.room.Embedded

data class GameWithResultsDto(
    @Embedded val game: GameEntity,
    @Embedded val player: PlayerEntity,
    val totalPoints: Int
)