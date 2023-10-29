package cz.ackee.testtask.rm.repository.list.domain.usecase

import cz.ackee.testtask.rm.repository.list.domain.repository.AllCharactersRepository
import cz.ackee.testtask.rm.repository.list.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCaseImpl(
    private val allCharactersRepository: AllCharactersRepository
) : GetAllCharactersUseCase {
    override fun invoke(page: Int): Flow<CharactersResponse> =
        allCharactersRepository.getAllCharacters(page)
}