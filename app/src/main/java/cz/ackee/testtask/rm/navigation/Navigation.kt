package cz.ackee.testtask.rm.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.feature.detail.system.DetailScreen
import cz.ackee.testtask.rm.feature.favorite.system.FavoriteScreen
import cz.ackee.testtask.rm.feature.list.system.CharactersListScreen
import timber.log.Timber

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.ListScreen.route
    ) {
        composable(NavigationScreen.ListScreen.route) {
            CharactersListScreen(navController = navController)
        }
        composable(NavigationScreen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController)
        }
        composable(NavigationScreen.DetailScreen.route) {
            val characterId = it.arguments?.runCatching { getString("characterId")?.toInt() }?.getOrNull()

            if (characterId != null) {
                DetailScreen(
                    navController = navController,
                    characterId = characterId
                )
            } else {
                val toast = Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.error),
                    Toast.LENGTH_SHORT
                )

                LaunchedEffect(Unit) {
                    toast.show()
                    Timber.e("Null ID passed to character detail nav.")
                    navController.popBackStack()
                }
            }
        }
    }
}