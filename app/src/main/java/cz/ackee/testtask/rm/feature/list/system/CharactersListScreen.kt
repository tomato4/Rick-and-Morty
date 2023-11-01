package cz.ackee.testtask.rm.feature.list.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.feature.list.presentation.ListAllCharactersViewModel
import cz.ackee.testtask.rm.navigation.NavigationScreen
import cz.ackee.testtask.rm.ui.component.AppScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersListScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<ListAllCharactersViewModel>()

    AppScaffold(
        title = stringResource(R.string.title_characters),
        navController = navController
    ) {
        CharactersListScreenImpl(
            charactersPagination = viewModel.paginationDataState.value,
            onClick = {
                navController.navigate(
                    NavigationScreen.DetailScreen.route
                        .replace("{characterId}", it.id.toString())
                )
            }
        )
    }
}