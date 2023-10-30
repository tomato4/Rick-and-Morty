package cz.ackee.testtask.rm.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.ui.theme.RickAndMortyTheme

@Composable
fun CharactersList(
    characters: List<Character>,
    onClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        items(
            items = characters
        ) {
            CharactersListItem(
                character = it,
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
@Preview
fun CharactersListPreview() {
    CharactersList(
        characters = List(10) {
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
        },
        onClick = {}
    )
}
