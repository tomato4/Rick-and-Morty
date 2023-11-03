package cz.ackee.testtask.rm.repository.detail.domain.usecase

import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface AreCharactersFavoriteUseCase {
    operator fun invoke(characters: List<Character>): Flow<CharactersResponse>
}