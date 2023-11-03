package cz.ackee.testtask.rm.feature.favorite.system

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.feature.favorite.presentation.FavoriteCharactersViewModel
import cz.ackee.testtask.rm.navigation.NavigationScreen
import cz.ackee.testtask.rm.ui.component.AppScaffold
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun FavoriteScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<FavoriteCharactersViewModel>()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    DisposableEffect(currentBackStackEntry) {
        Timber.d("enter")
        onDispose {
            Timber.d("leave")
        }
    }

    AppScaffold(
        title = stringResource(R.string.title_favorites),
        navController = navController
    ) {
        FavoriteScreenImpl(
            charactersPagination = viewModel.paginationData.value,
            onClick = {
                navController.navigate(
                    NavigationScreen.DetailScreen.route
                        .replace("{characterId}", it.id.toString())
                )
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.onLoadMore()
    }
}