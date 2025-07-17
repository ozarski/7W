package com.example.the7wonders.ui.tabsScreen.playersTab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.domain.model.PlayerModel
import com.example.the7wonders.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PlayerListViewModel @Inject constructor(private val playerRepository: PlayerRepository) :
    ViewModel() {

    private val _state = mutableStateOf(PlayerListState(emptyList()))
    val state: State<PlayerListState> = _state

    init {
        loadPlayers()
    }

    fun loadPlayers() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            playerRepository.getPlayers().catch {
                _state.value = _state.value.copy(isLoading = false)
            }.collect { players ->
                _state.value =
                    _state.value.copy(
                        isLoading = false,
                        playerList = players.sortedBy { it.name }.sortedByDescending { it.wins })
            }
        }
    }

    fun generateMockData(): List<PlayerModel> {
        val names = listOf(
            "Wojtek",
            "Szymon",
            "Kamil",
            "Kalina",
            "Kamila",
            "Kasia",
        )
        val players = mutableListOf<PlayerModel>()
        names.forEachIndexed { index, name ->
            players.add(
                PlayerModel(
                    id = index + 1L,
                    name = name,
                    wins = (0..20).random(),
                    games = (50..100).random(),
                    topScore = (50..70).random(),
                    avgPlacement = Random.nextDouble(1.0, 6.0)
                )
            )
        }
        return players
    }

    fun toggleDeletePopup(playerModel: PlayerModel?) {
        _state.value = _state.value.copy(
            deletePopupVisible = !_state.value.deletePopupVisible,
            popupPlayerModel = playerModel
        )
    }

    fun deletePlayer() {
        val playerModel = _state.value.popupPlayerModel ?: return
        viewModelScope.launch {
            playerRepository.deletePlayer(playerModel)
            toggleDeletePopup(null)
        }
    }
}