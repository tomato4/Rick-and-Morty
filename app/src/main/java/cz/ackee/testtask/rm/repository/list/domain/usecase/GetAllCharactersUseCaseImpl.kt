package cz.ackee.testtask.rm.repository.list.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetAllCharactersUseCase {
    override fun invoke(page: Int): Flow<CharactersResponse> =
        charactersRepository.getAllCharacters(page)
}