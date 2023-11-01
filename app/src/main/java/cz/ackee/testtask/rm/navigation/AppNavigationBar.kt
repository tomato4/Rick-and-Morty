package cz.ackee.testtask.rm.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.ui.component.AppNavigationBarItem

@Composable
fun AppNavigationBar(
    navController: NavController
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        AppNavigationBarItem(
            navController = navController,
            navigationScreen = NavigationScreen.ListScreen,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_character),
                    contentDescription = stringResource(R.string.nav_characters)
                )
            },
            label = stringResource(R.string.nav_characters)
        )

        AppNavigationBarItem(
            navController = navController,
            navigationScreen = NavigationScreen.FavoriteScreen,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = stringResource(R.string.nav_favorites)
                )
            },
            label = stringResource(R.string.nav_favorites)
        )
    }
}