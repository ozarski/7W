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
import com.example.the7wonders.ui.base.ConfirmationPopup
import com.example.the7wonders.ui.base.LoadingScreen
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.deletePopupVisible) {
        ConfirmationPopup(
            title = stringResource(R.string.are_you_sure),
            message = stringResource(R.string.delete_player_confirmation_message),
            onNegativeClick = { viewModel.toggleDeletePopup(null) },
            onPositiveClick = { viewModel.deletePlayer() },
            positiveButtonText = stringResource(R.string.yes_button_label),
            negativeButtonText = stringResource(R.string.no_button_label)
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    } else if (state.playerList.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(stringResource(R.string.no_players_found), style = Typography.labelMedium)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), state = state.gridState) {
                items(state.playerList.size) { index ->
                    PlayerListItem(
                        state.playerList[index],
                        onClick = { id -> //TODO("Navigate to player details screen")
                        },
                        onHold = { playerModel ->
                            viewModel.toggleDeletePopup(playerModel)
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