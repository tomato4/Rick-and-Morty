package cz.ackee.testtask.rm.repository.detail.domain.usecase

import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.domain.repository.CharactersResponse
import cz.ackee.testtask.rm.repository.favorite.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.Flow

class AreCharactersFavoriteUseCaseImpl(
    private val favoriteCharacterRepository: FavoriteCharacterRepository
) : AreCharactersFavoriteUseCase {
    override fun invoke(characters: List<Character>): Flow<CharactersResponse> =
        favoriteCharacterRepository.areCharactersFavorite(characters.map { it.id })
}