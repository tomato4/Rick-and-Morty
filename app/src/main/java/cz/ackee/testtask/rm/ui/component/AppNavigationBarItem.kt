package cz.ackee.testtask.rm.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import cz.ackee.testtask.rm.navigation.NavigationScreen

@Composable
fun RowScope.AppNavigationBarItem(
    navController: NavController,
    navigationScreen: NavigationScreen,
    icon: @Composable () -> Unit,
    label: String? = null
) {
    val selected = navController.currentDestination?.route == navigationScreen.route

    NavigationBarItem(
        icon = icon,
        label = label?.let {
            {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    softWrap = false
                )
            }
        },
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.surfaceTint,
            selectedTextColor = MaterialTheme.colorScheme.surfaceTint,
            indicatorColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onTertiary,
            unselectedTextColor = MaterialTheme.colorScheme.onTertiary
        ),
        onClick = if (!selected) { { navController.navigate(navigationScreen.route) } } else { {} }
    )
}