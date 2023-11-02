package cz.ackee.testtask.rm.repository.favorite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.ackee.testtask.rm.repository.favorite.data.dao.FavoriteCharacterDao
import cz.ackee.testtask.rm.repository.favorite.data.model.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 1, exportSchema = false)
abstract class FavoriteCharacterDatabaseImpl : RoomDatabase(), FavoriteCharacterDatabase {
    abstract override fun favoriteCharacterDao(): FavoriteCharacterDao
}