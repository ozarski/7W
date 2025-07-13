package com.example.the7wonders.ui.tabsScreen.gamesTab

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.LoadingScreen
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun GameListScreen(
    viewModel: GameListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.value.isLoading) {
        LoadingScreen()
    } else if (!state.value.isLoading && state.value.gameList.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(stringResource(R.string.empty_games_list), style = Typography.labelMedium)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(state = state.value.lazyListState) {
                items(state.value.gameList.size) { index ->
                    GameListItem(
                        game = state.value.gameList[index],
                        modifier = Modifier.padding(Dimens.paddingMedium)
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(Dimens.lazyColumnBottomSpacing))
                }
            }
        }
    }
}