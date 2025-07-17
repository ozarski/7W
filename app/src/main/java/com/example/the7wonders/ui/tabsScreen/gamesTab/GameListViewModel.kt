package com.example.the7wonders.ui.tabsScreen.gamesTab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.domain.model.GameModel
import com.example.the7wonders.domain.repository.GameRepository
import com.example.the7wonders.domain.repository.PlayerResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val playerResultRepository: PlayerResultRepository
) :
    ViewModel() {

    private val _state = mutableStateOf(GameListState(emptyList()))
    val state: State<GameListState> = _state

    init {
        viewModelScope.launch {
            loadGames()
        }
    }

    fun loadGames() {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            gameRepository.getGames().catch {
                _state.value = _state.value.copy(isLoading = false)
            }.collect { games ->
                _state.value = _state.value.copy(isLoading = false, gameList = games)
            }
        }
    }

    fun generateMockData(n: Int): List<GameModel> {
        val names = listOf(
            "Wojtek",
            "Szymon",
            "Kamil",
            "Kalina",
            "Kamila",
            "Kasia",
        )
        val games = mutableListOf<GameModel>()
        for (i in 0..n) {
            val scores = (30..70).shuffled().take(6)
            val playerScores = names.mapIndexed { index, name ->
                Pair(name, scores[index])
            }
            games.add(
                GameModel(
                    id = i + 1L,
                    playerScores = playerScores,
                    date = Calendar.getInstance().timeInMillis
                )
            )
        }
        return games
    }

    fun toggleDeletePopup(gameModel: GameModel?) {
        _state.value = _state.value.copy(
            deletePopupVisible = !_state.value.deletePopupVisible,
            popupGameModel = gameModel
        )
    }

    fun deleteGame() {
        val gameModel = _state.value.popupGameModel ?: return
        viewModelScope.launch {
            gameRepository.deleteGame(gameModel)
            if (gameModel.id != null) {
                playerResultRepository.deletePlayerResultsForGame(gameModel.id)
            }
            toggleDeletePopup(null)
        }
    }
}