package com.example.the7wonders.ui.addGameScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.the7wonders.domain.model.AddPlayerItem
import com.example.the7wonders.domain.model.PlayerResultItem
import com.example.the7wonders.domain.model.PlayersPointTypeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor() : ViewModel() {

    val maxPlayers = 7
    private val _state = mutableStateOf(AddGameState(emptyList()))
    val state: State<AddGameState> = _state

    init {
        insertMockData(20)
    }

    private fun addPlayer(playerIndex: Int) {
        val ordinal = getNextPlayerNumber()
        if (ordinal > maxPlayers) return
        val modifiedPlayers = _state.value.availablePlayers.toMutableList()
        modifiedPlayers[playerIndex] =
            modifiedPlayers[playerIndex].copy(isPlaying = true, ordinal = ordinal)
        _state.value = _state.value.copy(
            availablePlayers = modifiedPlayers
        )
    }

    private fun removePlayer(playerIndex: Int) {
        var modifiedPlayers = _state.value.availablePlayers.toMutableList()
        val playerOrdinal = modifiedPlayers[playerIndex].ordinal
        if (playerOrdinal == null) return
        modifiedPlayers[playerIndex] =
            modifiedPlayers[playerIndex].copy(isPlaying = false, ordinal = null)
        modifiedPlayers = modifiedPlayers.map { player ->
            if (player.isPlaying && player.ordinal != null && player.ordinal > playerOrdinal) {
                player.copy(
                    ordinal = player.ordinal - 1
                )
            } else {
                player
            }
        }.toMutableList()

        _state.value = _state.value.copy(
            availablePlayers = modifiedPlayers
        )
    }

    fun togglePlayer(playerIndex: Int) {
        if (_state.value.availablePlayers[playerIndex].isPlaying) {
            removePlayer(playerIndex)
        } else {
            addPlayer(playerIndex)
        }
    }

    fun getNextPlayerNumber(): Int {
        return _state.value.availablePlayers.count { it.isPlaying } + 1
    }

    fun confirmPlayers() {
        val selectedPlayers = _state.value.availablePlayers.filter { player -> player.isPlaying }
            .sortedBy { player -> player.ordinal }
        if (selectedPlayers.size > 1) {
            _state.value = _state.value.copy(
                selectedPlayers = selectedPlayers,
                gamePhase = GamePhase.PointInput
            )
        }
        initializePoints()
    }

    fun initializePoints() {
        val points = PointType.entries.flatMap { pointType ->
            _state.value.selectedPlayers.map { player ->
                PlayersPointTypeItem(
                    playerID = player.id,
                    playerName = player.name,
                    pointType = pointType,
                    value = 0
                )
            }
        }.toMutableList()
        val currentInputPoint = points.popOrNull()
        _state.value = _state.value.copy(pointQueue = points, currentInputPoint = currentInputPoint)
    }

    fun updateCurrentPointValue(value: String) {
        if (value.isEmpty()) {
            return
        } else {
            try {
                _state.value = _state.value.copy(
                    currentInputPoint = _state.value.currentInputPoint?.copy(
                        value = value.toInt()
                    )
                )
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun insertPointValue() {
        val pointQueueMutable = _state.value.pointQueue.toMutableList()
        val inputValue = _state.value.currentInputPoint
        if (inputValue == null) return
        val confirmedPointsMutable = _state.value.confirmedPoints.toMutableList()
        confirmedPointsMutable.push(inputValue)
        val newCurrentPoint = pointQueueMutable.popOrNull()
        _state.value = _state.value.copy(
            pointQueue = pointQueueMutable,
            confirmedPoints = confirmedPointsMutable,
            currentInputPoint = newCurrentPoint
        )
        if (_state.value.currentInputPoint == null) {
            finishGame()
        }
    }

    fun rollBackPointValue() {
        if (_state.value.confirmedPoints.isEmpty()) return
        val confirmedPointsMutable = _state.value.confirmedPoints.toMutableList()
        val currentInputPoint = _state.value.currentInputPoint
        if (currentInputPoint == null) return
        val newCurrentInputPoint = confirmedPointsMutable.popOrNull()
        val pointQueueMutable = _state.value.pointQueue.toMutableList()
        pointQueueMutable.push(currentInputPoint)
        _state.value = _state.value.copy(
            pointQueue = pointQueueMutable,
            confirmedPoints = confirmedPointsMutable,
            currentInputPoint = newCurrentInputPoint
        )
    }

    fun finishGame() {
        calculateResults()
        _state.value = _state.value.copy(
            gamePhase = GamePhase.Results
        )
    }

    fun calculateResults() {
        val scoreList = _state.value.selectedPlayers
            .map { player ->
                val playerScores = _state.value.confirmedPoints
                    .filter { score -> score.playerID == player.id }
                    .map { score -> Pair(score.pointType, score.value) }
                val playerTotalScore = playerScores.sumOf { it.second }
                PlayerResultItem(player.id, player.name, playerTotalScore, 0, playerScores)
            }.sortedByDescending {
                it.totalScore
            }.mapIndexed { index, result ->
                result.copy(placement = index + 1)
            }

        _state.value = _state.value.copy(results = scoreList)
    }

    fun insertMockData(n: Int) {
        val players = mutableListOf<AddPlayerItem>()
        for (i in 0..n) {
            players.add(
                AddPlayerItem(
                    id = i + 1L,
                    name = "Player $i",
                    isPlaying = false,
                    ordinal = null
                )
            )
        }
        _state.value = _state.value.copy(availablePlayers = players)
    }
}

fun <T> MutableList<T>.push(item: T) {
    add(0, item)
}

fun <T> MutableList<T>.popOrNull(): T? {
    return if (isEmpty()) null else removeAt(0)
}