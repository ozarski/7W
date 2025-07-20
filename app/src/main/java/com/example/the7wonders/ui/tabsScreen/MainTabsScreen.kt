package com.example.the7wonders.ui.tabsScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.the7wonders.ui.Screens
import com.example.the7wonders.ui.base.BackgroundOrientation
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.ConfirmationPopup
import com.example.the7wonders.ui.tabsScreen.gamesTab.GameListScreen
import com.example.the7wonders.ui.tabsScreen.playersTab.PlayerListScreen
import com.example.the7wonders.ui.tabsScreen.playersTab.addPlayer.AddPlayerPopup
import com.example.the7wonders.ui.theme.Dimens
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun MainTabsScreen(
    viewModel: MainTabsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value


    if (state.addPlayerPopupVisible) {
        AddPlayerPopup(
            onDismiss = { viewModel.hideAddPlayerPopup() },
            onAdd = { viewModel.addPlayer(it) }
        )
    }
    val databaseExportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/octet-stream")
    ) { uri ->
        uri?.let {
            viewModel.exportDatabase(it)
        }
    }

    if (state.exportDatabasePopupVisible) {
        ConfirmationPopup(
            title = "Export database?",
            message = "",
            positiveButtonText = "Yes",
            negativeButtonText = "No",
            onNegativeClick = {
                viewModel.hideExportDatabasePopup()
            },
            onPositiveClick = {
                val dateFormat = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss")
                val date = Calendar.getInstance().toInstant()
                    .atZone(ZoneId.systemDefault()).format(dateFormat)
                databaseExportLauncher.launch("gameDB_${date}.db")
            }
        )
    }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Crossfade(
                targetState = state.selectedTab,
                animationSpec = tween(Dimens.ANIMATION_DURATION_SHORT)
            ) { tab ->
                when (tab) {
                    MainTabs.Games -> {
                        BaseBackground(
                            modifier = Modifier.fillMaxSize(),
                            orientation = BackgroundOrientation.Horizontal
                        ) {
                            GameListScreen()
                        }
                    }

                    MainTabs.Players -> {
                        BaseBackground(
                            modifier = Modifier.fillMaxSize(),
                            flip = true,
                            orientation = BackgroundOrientation.Horizontal
                        ) {
                            PlayerListScreen()
                        }
                    }
                }
            }
        }
        TabsBar(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            onPlayerAdd = {
                viewModel.showAddPlayerPopup()
            },
            onGameAdd = {
                navController.navigate(Screens.AddGame.route)
            }
        ) { tab ->
            viewModel.selectTab(tab)
        }
    }
}