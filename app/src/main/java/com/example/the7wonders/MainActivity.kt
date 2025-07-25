package com.example.the7wonders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.the7wonders.ui.Screens
import com.example.the7wonders.ui.addGameScreen.AddGameScreen
import com.example.the7wonders.ui.gameDetailsScreen.GameDetailsScreen
import com.example.the7wonders.ui.tabsScreen.MainTabsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Column {
        NavHost(
            navController = navController,
            startDestination = Screens.MainTabs.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(Screens.MainTabs.route) {
                MainTabsScreen(navController = navController)
            }
            composable(
                Screens.AddGame.route,
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it },
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it },
                    )
                },
            ) {
                AddGameScreen(navController = navController)
            }
            composable(
                Screens.GameDetails.route + "/{${Screens.GAME_DETAILS_ID_PARAM}}",
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it },
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it },
                    )
                },
            ) {
                GameDetailsScreen(navController)
            }
        }
    }
}