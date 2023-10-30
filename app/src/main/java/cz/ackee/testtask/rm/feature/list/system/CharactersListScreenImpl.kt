package cz.ackee.testtask.rm.feature.list.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.ui.component.CharactersList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreenImpl(
    charactersPagination: PaginationData<Character>,
    onClick: (Character) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.title_characters),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                modifier = Modifier
                    .shadow(8.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CharactersList(
                charactersPagination = charactersPagination,
                onClick = onClick,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
@Preview
fun CharactersListScreenImplPreview() {
    CharactersListScreenImpl(
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
