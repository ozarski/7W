package com.example.the7wonders.ui.addGameScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun AddGameScreen(viewModel: AddGameViewModel = hiltViewModel(), navController: NavHostController) {
    val state = viewModel.state.value
    Crossfade(targetState = state.gamePhase) { gamePhase ->
        when (gamePhase) {
            GamePhase.PlayerSelection -> {
                AddPlayersScreen()
            }

            GamePhase.PointInput -> {
                PointInputScreen()
            }

            else -> {
                //TODO("Display game results screen")
            }
        }
    }
    BackHandler {
        //TODO("Implement confirmation popup")
        navController.popBackStack()
    }
}