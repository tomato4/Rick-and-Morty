package cz.ackee.testtask.rm.navigation

sealed class NavigationScreen(val route: String) {
    object ListScreen: NavigationScreen("list")
    object FavoriteScreen: NavigationScreen("favorite")
    object DetailScreen: NavigationScreen("detail/{characterId}")
}
