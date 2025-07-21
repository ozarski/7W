package com.example.the7wonders.ui.tabsScreen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.data.DatabaseExporter
import com.example.the7wonders.domain.model.PlayerModel
import com.example.the7wonders.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTabsViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val databaseExporter: DatabaseExporter
) :
    ViewModel() {
    private val _state = mutableStateOf(MainTabsState(selectedTab = MainTabs.Games))
    val state: State<MainTabsState> = _state

    fun selectTab(tab: MainTabs) {
        println(_state.value.selectedTab)
        _state.value = _state.value.copy(selectedTab = tab)
        println(_state.value.selectedTab)
    }

    fun showAddPlayerPopup() {
        _state.value = _state.value.copy(addPlayerPopupVisible = true)
    }

    fun hideAddPlayerPopup() {
        _state.value = _state.value.copy(addPlayerPopupVisible = false)
    }

    fun showExportDatabasePopup() {
        _state.value = _state.value.copy(exportDatabasePopupVisible = true)
    }

    fun hideExportDatabasePopup() {
        _state.value = _state.value.copy(exportDatabasePopupVisible = false)
    }

    fun showSettingsPopup() {
        _state.value = _state.value.copy(settingsPopupVisible = true)
    }

    fun hideSettingsPopup() {
        _state.value = _state.value.copy(settingsPopupVisible = false)
    }

    fun exportDatabase(uri: Uri) {
        viewModelScope.launch {
            databaseExporter.exportDatabaseToUri(uri)
            hideExportDatabasePopup()
        }
    }

    fun addPlayer(name: String) {
        viewModelScope.launch {
            playerRepository.addPlayer(PlayerModel(name = name))
            hideAddPlayerPopup()
        }
    }
}


