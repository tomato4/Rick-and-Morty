package cz.ackee.testtask.rm.repository.detail.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharacterDetailUseCase {
    override fun invoke(id: Int): Flow<CharacterResponse> =
        charactersRepository.getCharacter(id)
}