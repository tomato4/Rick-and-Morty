package cz.ackee.testtask.rm.feature.detail.system

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
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
        showNavigation = false,
        actions = {
            IconButton(
                onClick = {
                    if (viewModel.characterResponseState.value.getSuccessData()?.favorite == true) {
                        viewModel.changeLike(false)
                    } else if (viewModel.characterResponseState.value.getSuccessData()?.favorite == false) {
                        viewModel.changeLike(true)
                    }
                }
            ) {
                if (viewModel.characterResponseState.value.getSuccessData()?.favorite == true) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(R.string.alt_unlike),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.star_outline),
                        contentDescription = stringResource(R.string.alt_like),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) {
        DetailScreenImpl(
            character = viewModel.characterResponseState.value
        )
    }

    LaunchedEffect(Unit) {
        viewModel.onLoadData()
    }
}