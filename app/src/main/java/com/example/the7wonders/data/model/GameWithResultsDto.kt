package com.example.the7wonders.data.model

import androidx.room.Embedded

// Data layer DTO (matches SQL query structure)
data class GameWithResultsDto(
    @Embedded val game: GameEntity,          // Game fields
    @Embedded val player: PlayerEntity,      // Player fields
    val totalPoints: Int                           // From PlayerResult
)