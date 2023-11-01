package cz.ackee.testtask.rm.feature.favorite.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.ui.component.AppScaffold

@Composable
fun FavoriteScreen(
    navController: NavController
) {
    AppScaffold(
        title = stringResource(R.string.title_favorites),
        navController = navController
    ) {
//        FavoriteScreenImpl(
//            charactersPagination = ,
//            onClick =
//        )
    }
}