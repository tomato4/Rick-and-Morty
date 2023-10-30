package cz.ackee.testtask.rm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.feature.list.system.CharactersListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.ListScreen.route
    ) {
        composable(NavigationScreen.ListScreen.route) {
            CharactersListScreen(
                navController = navController
            )
        }
        composable(NavigationScreen.DetailScreen.route) {

        }
    }
}