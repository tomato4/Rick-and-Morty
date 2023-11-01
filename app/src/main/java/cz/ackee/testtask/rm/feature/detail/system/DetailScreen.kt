package cz.ackee.testtask.rm.feature.detail.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import cz.ackee.testtask.rm.feature.detail.presentation.CharacterDetailViewModel
import cz.ackee.testtask.rm.ui.component.AppScaffold
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parameterArrayOf

@Composable
fun DetailScreen(
    navController: NavController,
    characterId: Int
) {
    val viewModel: CharacterDetailViewModel = koinViewModel(
        parameters = { parameterArrayOf(characterId) }
    )

    AppScaffold(
        title = viewModel.characterResponseState.value.getSuccessData()?.name.orEmpty(),
        navController = navController,
        showBackButton = true,
        showNavigation = false
    ) {
        DetailScreenImpl(
            character = viewModel.characterResponseState.value
        )
    }

    LaunchedEffect(Unit) {
        viewModel.onLoadData()
    }
}