package cz.ackee.testtask.rm.repository.favorite.data.repository

import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.Variables
import cz.ackee.testtask.rm.app.common.EmptyResponse
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.app.common.UiText
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.data.database.FavoriteCharacterDatabaseImpl
import cz.ackee.testtask.rm.repository.favorite.data.mapper.toDomain
import cz.ackee.testtask.rm.repository.favorite.data.mapper.toModel
import cz.ackee.testtask.rm.repository.favorite.domain.repository.CharactersResponse
import cz.ackee.testtask.rm.repository.favorite.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class FavoriteCharacterRepositoryImpl(
    private val database: FavoriteCharacterDatabaseImpl
) : FavoriteCharacterRepository {
    override fun getCharacters(page: Int): Flow<CharactersResponse> = flow {
        emit(Response.Loading)

        try {
            val result = database.favoriteCharacterDao().getCharacters(
                offset = (page - 1) * Variables.PAGE_SIZE,
                limit = Variables.PAGE_SIZE
            ).toDomain()

            emit(Response.Success(result))
        } catch (e: Throwable) {
            emit(Response.Error(UiText.StringResource(R.string.error)))
            Timber.e(e)
        }
    }.flowOn(Dispatchers.IO)

    override fun areCharactersFavorite(characters: List<Int>): Flow<CharactersResponse> = flow {
        emit(Response.Loading)

        try {
            val result = database.favoriteCharacterDao().getCharactersByIds(
                ids = characters
            ).toDomain()

            emit(Response.Success(result))
        } catch (e: Throwable) {
            emit(Response.Error(UiText.StringResource(R.string.error)))
            Timber.e(e)
        }
    }.flowOn(Dispatchers.IO)

    override fun addCharacter(character: Character): Flow<EmptyResponse> = flow {
        emit(Response.Loading)

        try {
            database.favoriteCharacterDao().insertCharacter(
                character = character.toModel()
            )

            emit(Response.Success(Unit))
        } catch (e: Throwable) {
            emit(Response.Error(UiText.StringResource(R.string.error)))
            Timber.e(e)
        }
    }

    override fun removeCharacter(character: Character): Flow<EmptyResponse> = flow {
        emit(Response.Loading)

        try {
            database.favoriteCharacterDao().deleteCharacter(
                character = character.toModel()
            )

            emit(Response.Success(Unit))
        } catch (e: Throwable) {
            emit(Response.Error(UiText.StringResource(R.string.error)))
            Timber.e(e)
        }
    }
}