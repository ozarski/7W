package com.example.the7wonders.ui.tabsScreen.gamesTab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.domain.model.GameModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(GameListState(emptyList()))
    val state: State<GameListState> = _state

    init {
        viewModelScope.launch {
            loadGames()
        }
    }

    suspend fun loadGames() {
        _state.value = _state.value.copy(isLoading = true)
        //TODO("Replace with loading data from the database")
        delay(500)
        _state.value = _state.value.copy(gameList = generateMockData(50), isLoading = false)
        //_state.value = _state.value.copy(gameList = emptyList(), isLoading = false)
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

    fun toggleDeletePopup(id: Long?) {
        _state.value = _state.value.copy(
            deletePopupVisible = !_state.value.deletePopupVisible,
            popupGameID = id
        )
    }

    fun deleteGame() {
        //TODO("delete game")
    }
}