package cz.ackee.testtask.rm.repository.favorite.domain.usecase

import cz.ackee.testtask.rm.app.common.EmptyResponse
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.Flow

class ChangeCharacterFavUseCaseImpl(
    private val favoriteCharacterRepository: FavoriteCharacterRepository
) : ChangeCharacterFavUseCase {
    override fun invoke(character: Character, favorite: Boolean): Flow<EmptyResponse> =
        if (favorite) {
            favoriteCharacterRepository.addCharacter(character)
        } else {
            favoriteCharacterRepository.removeCharacter(character)
        }
}