package cz.ackee.testtask.rm.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.repository.common.model.Character

@Composable
fun CharactersList(
    charactersPagination: PaginationData<Character>,
    onClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        items(
            items = charactersPagination.data
        ) {
            CharactersListItem(
                character = it,
                onClick = { onClick(it) }
            )
        }

        if (!charactersPagination.endReached) {
            item {
                Column(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (charactersPagination.isLoadingPage()) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.surfaceTint)
                    }
                }
            }
        }
    }

    PaginationHandler(
        state = lazyListState,
        paginationData = charactersPagination
    )
}

@Composable
@Preview
fun CharactersListPreview() {
    CharactersList(
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
        onClick = {},
    )
}
