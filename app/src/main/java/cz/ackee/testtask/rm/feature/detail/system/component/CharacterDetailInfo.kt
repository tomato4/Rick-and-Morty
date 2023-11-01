package cz.ackee.testtask.rm.feature.detail.system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.ackee.testtask.rm.ui.theme.RickAndMortyTheme

@Composable
fun CharacterDetailInfo(
    name: String,
    value: String?
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = if (value.isNullOrEmpty()) "-" else value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun CharacterDetailInfoPreview() {
    RickAndMortyTheme {
        CharacterDetailInfo(
            name = "Name",
            value = "Rick Sanchez"
        )
    }
}
