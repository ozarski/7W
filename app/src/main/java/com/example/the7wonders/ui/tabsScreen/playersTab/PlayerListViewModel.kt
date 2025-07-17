package com.example.the7wonders.ui.tabsScreen.playersTab

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.domain.model.PlayerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PlayerListViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(PlayerListState(emptyList()))
    val state: State<PlayerListState> = _state

    init {
        viewModelScope.launch {
            loadPlayers()
        }
    }

    suspend fun loadPlayers() {
        _state.value = _state.value.copy(isLoading = true)
        delay(500)
        val alotofdata = mutableListOf<PlayerModel>()
        for (i in 0..20) {
            alotofdata.addAll(generateMockData())
        }
        _state.value = _state.value.copy(playerList = alotofdata, isLoading = false)
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

    fun toggleDeletePopup(id: Long?) {
        _state.value = _state.value.copy(deletePopupVisible = !_state.value.deletePopupVisible)
    }

    fun deletePlayer() {
        //TODO("Delete player")
    }
}