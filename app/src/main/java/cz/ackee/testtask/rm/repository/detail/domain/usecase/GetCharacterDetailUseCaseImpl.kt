package cz.ackee.testtask.rm.repository.detail.domain.usecase

import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map

class GetCharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository,
    private val areCharactersFavoriteUseCase: AreCharactersFavoriteUseCase
) : GetCharacterDetailUseCase {
    override fun invoke(id: Int): Flow<CharacterResponse> =
        charactersRepository
            .getCharacter(id)
            .map {
                if (it is Response.Success) {
                    val favResponse = areCharactersFavoriteUseCase(listOf(it.data)).lastOrNull()

                    if (favResponse?.getSuccessData().orEmpty().isNotEmpty()) {
                        Response.Success(it.data.copy(favorite = true))
                    } else {
                        it
                    }
                } else {
                    it
                }
            }
}