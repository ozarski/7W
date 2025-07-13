package com.example.the7wonders.ui.tabsScreen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.tabsScreen.gamesTab.GameListScreen
import com.example.the7wonders.ui.tabsScreen.playersTab.PlayerListScreen
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun MainTabsScreen(
    viewModel: MainTabsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

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
                selected = state.selectedTab,
                modifier = Modifier.align(alignment = Alignment.BottomCenter)
            ) { tab ->
                viewModel.selectTab(tab)
            }
        }
    }
}