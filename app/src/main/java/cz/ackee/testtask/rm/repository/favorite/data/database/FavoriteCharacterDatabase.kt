package cz.ackee.testtask.rm.repository.favorite.data.database

import cz.ackee.testtask.rm.repository.favorite.data.dao.FavoriteCharacterDao

interface FavoriteCharacterDatabase {
    fun favoriteCharacterDao(): FavoriteCharacterDao
}
