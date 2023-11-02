package cz.ackee.testtask.rm.feature.favorite.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.ui.component.CharactersList
import cz.ackee.testtask.rm.ui.theme.RickAndMortyTheme

@Composable
fun FavoriteScreenImpl(
    charactersPagination: PaginationData<Character>,
    onClick: (Character) -> Unit,
) {
    CharactersList(
        charactersPagination = charactersPagination,
        onClick = onClick
    )
}

@Composable
@Preview
fun FavoriteScreenImplPreview() {
    RickAndMortyTheme {
        FavoriteScreenImpl(
            charactersPagination = PaginationData(
                data = List(10) {
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
            ),
            onClick = {}
        )
    }
}
