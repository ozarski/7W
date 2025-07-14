package com.example.the7wonders.ui.addGameScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun AddGameScreen(viewModel: AddGameViewModel = hiltViewModel(), navController: NavHostController) {
    AddPlayersScreen()
}