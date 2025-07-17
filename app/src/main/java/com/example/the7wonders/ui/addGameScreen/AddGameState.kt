package com.example.the7wonders.ui.addGameScreen

import com.example.the7wonders.domain.model.AddPlayerToGameModel
import com.example.the7wonders.domain.model.PlayerPointTypeModel
import com.example.the7wonders.domain.model.PlayerResultModel


enum class GamePhase {
    PlayerSelection,
    PointInput,
    Results
}

data class AddGameState(
    val availablePlayers: List<AddPlayerToGameModel>,
    val selectedPlayers: List<AddPlayerToGameModel> = emptyList(),
    val pointQueue: List<PlayerPointTypeModel> = emptyList(),
    val confirmedPoints: List<PlayerPointTypeModel> = emptyList(),
    val currentInputPoint: PlayerPointTypeModel? = null,
    val results: List<PlayerResultModel> = emptyList(),
    val isLoading: Boolean = false,
    val gamePhase: GamePhase = GamePhase.PlayerSelection
)
