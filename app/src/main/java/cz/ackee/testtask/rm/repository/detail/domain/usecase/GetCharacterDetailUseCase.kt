package cz.ackee.testtask.rm.repository.detail.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface GetCharacterDetailUseCase {
    operator fun invoke(id: Int): Flow<CharacterResponse>
}