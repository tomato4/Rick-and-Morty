package cz.ackee.testtask.rm.repository.list.domain.repository

import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import kotlinx.coroutines.flow.Flow

typealias CharactersResponse = Response<List<Character>>

interface AllCharactersRepository {
    fun getAllCharacters(page: Int): Flow<CharactersResponse>
}