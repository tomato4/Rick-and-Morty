package cz.ackee.testtask.rm.feature.favorite.system

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.feature.favorite.presentation.FavoriteCharactersViewModel
import cz.ackee.testtask.rm.navigation.NavigationScreen
import cz.ackee.testtask.rm.ui.component.AppScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<FavoriteCharactersViewModel>()

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