package cz.ackee.testtask.rm.repository.favorite.domain.usecase

import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import cz.ackee.testtask.rm.repository.favorite.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteCharactersUseCaseImpl(
    private val favoriteCharacterRepository: FavoriteCharacterRepository
) : GetFavoriteCharactersUseCase {
    override fun invoke(page: Int): Flow<CharactersResponse> =
        favoriteCharacterRepository.getCharacters(page)
}