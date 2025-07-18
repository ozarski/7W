package com.example.the7wonders.ui.tabsScreen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.the7wonders.ui.Screens
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.ConfirmationPopup
import com.example.the7wonders.ui.tabsScreen.gamesTab.GameListScreen
import com.example.the7wonders.ui.tabsScreen.playersTab.PlayerListScreen
import com.example.the7wonders.ui.tabsScreen.playersTab.addPlayer.AddPlayerPopup
import com.example.the7wonders.ui.theme.Dimens

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
    val activity = LocalContext.current.findActivity()

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
                viewModel.exportDatabase(activity)
            }
        )
    }

    BaseBackground(modifier = Modifier.fillMaxSize()) {
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
                            GameListScreen()
                        }

                        MainTabs.Players -> {
                            PlayerListScreen()
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
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No Activity found")
}
