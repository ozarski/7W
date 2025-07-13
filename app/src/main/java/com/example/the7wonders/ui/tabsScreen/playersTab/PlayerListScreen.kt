package com.example.the7wonders.ui.tabsScreen.playersTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.LoadingScreen
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel = hiltViewModel()
) {
    val state = viewModel.state


    if (state.value.isLoading) {
        LoadingScreen()
    } else if (!state.value.isLoading && state.value.playerList.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(stringResource(R.string.empty_games_list), style = Typography.labelMedium)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), state = state.value.gridState) {
                items(state.value.playerList.size) { index ->
                    PlayerListItem(state.value.playerList[index])
                }
                item {
                    Spacer(modifier = Modifier.size(Dimens.lazyColumnBottomSpacing))
                }
            }
        }
    }
}