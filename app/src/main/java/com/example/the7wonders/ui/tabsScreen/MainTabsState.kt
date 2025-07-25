package com.example.the7wonders.ui.tabsScreen

data class MainTabsState(
    val selectedTab: MainTabs,
    val addPlayerPopupVisible: Boolean = false,
    val exportDatabasePopupVisible: Boolean = false,
    val importDatabasePopupVisible: Boolean = false,
    val settingsPopupVisible: Boolean = false,
    val databaseReloadNeeded: Boolean = false
)
