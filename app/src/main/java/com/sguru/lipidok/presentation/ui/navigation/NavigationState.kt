package com.sguru.lipidok.presentation.ui.navigation

sealed class NavigationState(val baseRoute: String) {
    data object RoleSelectionScreen : NavigationState("ROLE_SELECTION")
    data object MainScreen : NavigationState("MAIN")
    data object DataBaseScreen : NavigationState("DATA_BASE")
    data object GeneralScreen : NavigationState("GENERAL")
}