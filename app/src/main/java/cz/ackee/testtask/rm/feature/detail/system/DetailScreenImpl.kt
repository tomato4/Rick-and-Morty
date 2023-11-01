package cz.ackee.testtask.rm.feature.detail.system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.feature.detail.system.component.CharacterDetailInfo
import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.ui.theme.RickAndMortyTheme

@Composable
fun DetailScreenImpl(
    character: CharacterResponse
) {
    if (character is Response.Success) {
        Surface(
            shadowElevation = 4.dp,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = character.data.imageUrl,
                        contentDescription = character.data.name,
                        placeholder = painterResource(R.drawable.ic_character),
                        modifier = Modifier
                            .weight(0.5f)
                            .clip(MaterialTheme.shapes.small)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text(
                            text = stringResource(R.string.detail_screen_name),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = character.data.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

                Divider(thickness = 1.dp)

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_status),
                        value = character.data.status
                    )

                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_species),
                        value = character.data.species
                    )

                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_type),
                        value = character.data.type
                    )

                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_gender),
                        value = character.data.gender
                    )

                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_origin),
                        value = character.data.origin
                    )

                    CharacterDetailInfo(
                        name = stringResource(R.string.detail_screen_location),
                        value = character.data.location
                    )
                }
            }
        }
    } else {
        Box {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    RickAndMortyTheme {
        DetailScreenImpl(
            character = Response.Success(
                Character(
                    id = 1,
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
            )
        )
    }
}
