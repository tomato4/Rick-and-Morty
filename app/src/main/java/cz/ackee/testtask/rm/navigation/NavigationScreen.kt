package cz.ackee.testtask.rm.navigation

sealed class NavigationScreen(val route: String) {
    data object ListScreen: NavigationScreen("list")
    data object FavoriteScreen: NavigationScreen("favorite")
    data object DetailScreen: NavigationScreen("detail/{characterId}")
}
