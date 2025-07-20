package com.example.the7wonders.ui.addGameScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.the7wonders.ui.addGameScreen.inputPoints.PointInputScreen
import com.example.the7wonders.ui.addGameScreen.playerSelection.AddPlayersScreen
import com.example.the7wonders.ui.addGameScreen.results.GameResultsScreen
import com.example.the7wonders.ui.base.BaseBackground

@Composable
fun AddGameScreen(viewModel: AddGameViewModel = hiltViewModel(), navController: NavHostController) {
    val state = viewModel.state.value
    BaseBackground {
        Crossfade(targetState = state.gamePhase) { gamePhase ->
            when (gamePhase) {
                GamePhase.PlayerSelection -> {
                    AddPlayersScreen()
                }

                GamePhase.PointInput -> {
                    PointInputScreen()
                }

                else -> {
                    GameResultsScreen(navController = navController)
                }
            }
        }
    }
    BackHandler {
        //TODO("Implement confirmation popup")
        navController.popBackStack()
    }
}