package cz.ackee.testtask.rm.repository.favorite.domain.repository

import cz.ackee.testtask.rm.app.common.EmptyResponse
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import kotlinx.coroutines.flow.Flow

typealias CharactersResponse = Response<List<Character>>

interface FavoriteCharacterRepository {
    fun getCharacters(page: Int): Flow<CharactersResponse>
    fun areCharactersFavorite(ids: List<Int>): Flow<CharactersResponse>
    fun addCharacter(character: Character): Flow<EmptyResponse>
    fun removeCharacter(character: Character): Flow<EmptyResponse>
}