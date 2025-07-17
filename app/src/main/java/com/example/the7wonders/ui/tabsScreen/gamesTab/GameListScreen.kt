package com.example.the7wonders.ui.tabsScreen.gamesTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.ConfirmationPopup
import com.example.the7wonders.ui.base.LoadingScreen
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun GameListScreen(
    viewModel: GameListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    if (state.deletePopupVisible) {
        ConfirmationPopup(
            title = stringResource(R.string.are_you_sure),
            message = stringResource(R.string.game_delete_confirmation_message),
            onNegativeClick = { viewModel.toggleDeletePopup(null) },
            onPositiveClick = { viewModel.deleteGame() },
            positiveButtonText = stringResource(R.string.yes_button_label),
            negativeButtonText = stringResource(R.string.no_button_label)
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    } else if (state.gameList.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(stringResource(R.string.empty_games_list), style = Typography.labelMedium)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(state = state.lazyListState) {
                items(state.gameList.size) { index ->
                    GameListItem(
                        game = state.gameList[index],
                        modifier = Modifier.padding(Dimens.paddingMedium),
                        onClick = {
                            //TODO("Navigate to game details screen")
                        },
                        onHold = { gameModel ->
                            viewModel.toggleDeletePopup(gameModel)
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(Dimens.lazyColumnBottomSpacing))
                }
            }
        }
    }
}