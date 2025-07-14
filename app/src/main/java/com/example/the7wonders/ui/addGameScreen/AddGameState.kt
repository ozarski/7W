package com.example.the7wonders.ui.addGameScreen

import com.example.the7wonders.domain.model.AddPlayerItem

data class AddGameState(
    val availablePlayers: List<AddPlayerItem>,
    val isLoading: Boolean = false
)
