package com.example.the7wonders.ui.tabsScreen.playersTab

import androidx.compose.foundation.lazy.grid.LazyGridState
import com.example.the7wonders.domain.model.PlayerItem

data class PlayerListState(
    val playerList: List<PlayerItem>,
    val isLoading: Boolean = false,
    val gridState: LazyGridState = LazyGridState(),
    val deletePopupVisible: Boolean = false,
    val popupPlayerID: Long? = null
)
