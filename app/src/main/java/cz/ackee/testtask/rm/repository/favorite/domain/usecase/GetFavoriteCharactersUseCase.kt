package cz.ackee.testtask.rm.repository.favorite.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface GetFavoriteCharactersUseCase {
    operator fun invoke(): Flow<CharactersResponse>
}