package cz.ackee.testtask.rm.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.repository.common.model.Character

@Composable
fun CharactersListItem(
    character: Character,
    onClick: () -> Unit
) {
    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(12.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = character.name,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = stringResource(R.string.favorite),
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    )
                }
                Text(
                    text = character.status,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
@Preview
fun CharactersListItemPreview() {
    CharactersListItem(
        character = Character(
            id = 0,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = null,
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            favorite = true
        ),
        onClick = {}
    )
}
