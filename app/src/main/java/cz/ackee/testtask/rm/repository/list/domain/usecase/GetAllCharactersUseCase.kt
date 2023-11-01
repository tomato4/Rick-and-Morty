package cz.ackee.testtask.rm.repository.list.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface GetAllCharactersUseCase {
    operator fun invoke(page: Int): Flow<CharactersResponse>
}