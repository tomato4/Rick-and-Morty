package cz.ackee.testtask.rm.repository.common.domain.repository

import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import kotlinx.coroutines.flow.Flow

typealias CharactersResponse = Response<List<Character>>
typealias CharacterResponse = Response<Character>

interface CharactersRepository {
    fun getAllCharacters(page: Int, name: String? = null): Flow<CharactersResponse>
    fun getSelectedCharacters(ids: List<Int>) : Flow<CharactersResponse>
    fun getCharacter(id: Int): Flow<CharacterResponse>
}