package cz.ackee.testtask.rm.feature.list.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.ackee.testtask.rm.feature.list.presentation.ListAllCharactersViewModel
import cz.ackee.testtask.rm.navigation.NavigationScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersListScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<ListAllCharactersViewModel>()

    CharactersListScreenImpl(
        characters = viewModel.paginationDataState.value.data,
        onClick = {
            navController.navigate(
                NavigationScreen.DetailScreen.route
                    .replace("{characterId}", it.id.toString())
            )
        }
    )
}