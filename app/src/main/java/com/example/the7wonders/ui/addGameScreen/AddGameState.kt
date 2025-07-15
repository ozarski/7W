package com.example.the7wonders.ui.addGameScreen

import com.example.the7wonders.domain.model.AddPlayerItem
import com.example.the7wonders.domain.model.PlayerResultItem
import com.example.the7wonders.domain.model.PlayersPointTypeItem


enum class GamePhase {
    PlayerSelection,
    PointInput,
    Results
}

data class AddGameState(
    val availablePlayers: List<AddPlayerItem>,
    val selectedPlayers: List<AddPlayerItem> = emptyList(),
    val pointQueue: List<PlayersPointTypeItem> = emptyList(),
    val confirmedPoints: List<PlayersPointTypeItem> = emptyList(),
    val currentInputPoint: PlayersPointTypeItem? = null,
    val results: List<PlayerResultItem> = emptyList(),
    val isLoading: Boolean = false,
    val gamePhase: GamePhase = GamePhase.PlayerSelection
)
