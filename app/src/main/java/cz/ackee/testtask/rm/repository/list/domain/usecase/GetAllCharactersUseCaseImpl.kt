package cz.ackee.testtask.rm.repository.list.domain.usecase

import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import cz.ackee.testtask.rm.repository.detail.domain.usecase.AreCharactersFavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map

class GetAllCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository,
    private val areCharactersFavoriteUseCase: AreCharactersFavoriteUseCase
) : GetAllCharactersUseCase {
    override fun invoke(page: Int, searchName: String?): Flow<CharactersResponse> =
        charactersRepository
            .getAllCharacters(page, searchName)
            .map { response ->
                if (response is Response.Success) {
                    val favResponse = areCharactersFavoriteUseCase(response.data).lastOrNull()

                    if (favResponse == null || favResponse !is Response.Success) {
                        response
                    } else {
                        val favIds = favResponse.data.map { it.id }

                        Response.Success(
                            response.data.map {character ->
                                if (character.id in favIds) {
                                    character.copy(favorite = true)
                                } else {
                                    character
                                }
                            }
                        )
                    }
                } else {
                    response
                }
            }
}