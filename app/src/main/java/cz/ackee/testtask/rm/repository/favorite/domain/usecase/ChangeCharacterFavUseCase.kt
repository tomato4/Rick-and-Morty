package cz.ackee.testtask.rm.repository.favorite.domain.usecase

import cz.ackee.testtask.rm.app.common.EmptyResponse
import cz.ackee.testtask.rm.repository.common.model.Character
import kotlinx.coroutines.flow.Flow

interface ChangeCharacterFavUseCase {
    operator fun invoke(character: Character, favorite: Boolean): Flow<EmptyResponse>
}