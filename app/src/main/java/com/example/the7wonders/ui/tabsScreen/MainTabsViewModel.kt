package com.example.the7wonders.ui.tabsScreen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the7wonders.data.DatabaseManager
import com.example.the7wonders.domain.model.PlayerModel
import com.example.the7wonders.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTabsViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val databaseManager: DatabaseManager
) :
    ViewModel() {
    private val _state = mutableStateOf(MainTabsState(selectedTab = MainTabs.Games))
    val state: State<MainTabsState> = _state

    fun selectTab(tab: MainTabs) {
        _state.value = _state.value.copy(selectedTab = tab)
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

    fun showImportDatabasePopup() {
        _state.value = _state.value.copy(importDatabasePopupVisible = true)
    }

    fun hideImportDatabasePopup() {
        _state.value = _state.value.copy(importDatabasePopupVisible = false)
    }

    fun showSettingsPopup() {
        _state.value = _state.value.copy(settingsPopupVisible = true)
    }

    fun hideSettingsPopup() {
        _state.value = _state.value.copy(settingsPopupVisible = false)
    }

    fun exportDatabase(uri: Uri) {
        viewModelScope.launch {
            databaseManager.exportDatabaseToUri(uri)
            hideExportDatabasePopup()
        }
    }

    fun importDatabase(uri: Uri) {
        viewModelScope.launch {
            val result = databaseManager.importDatabaseFromUri(uri)
            if (result.isSuccess) {
                _state.value = _state.value.copy(databaseReloadNeeded = true)
            }
            hideImportDatabasePopup()
        }
    }

    fun addPlayer(name: String) {
        viewModelScope.launch {
            playerRepository.addPlayer(PlayerModel(name = name))
            hideAddPlayerPopup()
        }
    }

    fun databaseReloaded() {
        _state.value = _state.value.copy(databaseReloadNeeded = false)
    }
}


