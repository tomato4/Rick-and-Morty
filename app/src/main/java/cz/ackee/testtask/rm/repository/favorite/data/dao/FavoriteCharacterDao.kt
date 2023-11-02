package cz.ackee.testtask.rm.repository.favorite.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.ackee.testtask.rm.repository.favorite.data.model.FavoriteCharacter

@Dao
interface FavoriteCharacterDao {
    @Query("SELECT * FROM favorite_character LIMIT :offset, :limit")
    fun getCharacters(offset: Int, limit: Int): List<FavoriteCharacter>

    @Query("SELECT * FROM favorite_character WHERE id = :id")
    fun getOneCharacter(id: Int): FavoriteCharacter?

    @Query("SELECT * FROM favorite_character WHERE id IN (:ids)")
    fun getCharactersByIds(ids: List<Int>): List<FavoriteCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: FavoriteCharacter)

    @Delete
    fun deleteCharacter(character: FavoriteCharacter)
}