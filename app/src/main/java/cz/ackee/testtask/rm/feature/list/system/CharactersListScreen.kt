package cz.ackee.testtask.rm.feature.list.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.ackee.testtask.rm.navigation.NavigationScreen
import cz.ackee.testtask.rm.repository.common.model.Character

@Composable
fun CharactersListScreen(
    navController: NavController
) {
    val characters = List(10) {
        Character(
            id = it,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = null,
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            favorite = true
        )
    }

    CharactersListScreenImpl(
        characters = characters,
        onClick = {
            navController.navigate(
                NavigationScreen.DetailScreen.route
                    .replace("{characterId}", it.id.toString())
            )
        }
    )
}