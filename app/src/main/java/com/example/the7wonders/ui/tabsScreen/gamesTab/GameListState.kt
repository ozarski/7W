package com.example.the7wonders.ui.tabsScreen.gamesTab

import androidx.compose.foundation.lazy.LazyListState
import com.example.the7wonders.domain.model.GameItem

data class GameListState(
    val gameList: List<GameItem>,
    val isLoading: Boolean = false,
    val lazyListState: LazyListState = LazyListState(),
    val deletePopupVisible: Boolean = false,
    val popupGameID: Long? = null
)
