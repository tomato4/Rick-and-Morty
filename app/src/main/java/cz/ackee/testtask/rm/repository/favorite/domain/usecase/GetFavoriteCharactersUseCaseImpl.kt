package cz.ackee.testtask.rm.repository.favorite.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

class GetFavoriteCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetFavoriteCharactersUseCase {
    override fun invoke(): Flow<CharactersResponse> =
        charactersRepository.getSelectedCharacters(listOf(5, 10)) // TODO Implement
}