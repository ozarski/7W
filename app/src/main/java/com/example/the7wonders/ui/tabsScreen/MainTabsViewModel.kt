package com.example.the7wonders.ui.tabsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

//@HiltViewModel
class MainTabsViewModel @Inject constructor() : ViewModel() {
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

    fun addPlayer(name: String) {
        //TODO("Not yet implemented")
        println("Added player $name")
        hideAddPlayerPopup()
    }
}


