package cz.ackee.testtask.rm.repository.favorite.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_character")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val status: String,
    @ColumnInfo val imageUrl: String,
)
