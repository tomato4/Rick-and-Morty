package cz.ackee.testtask.rm.repository.common.data.repository

import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.app.common.UiText
import cz.ackee.testtask.rm.repository.common.data.mapper.toDomain
import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersRepository
import cz.ackee.testtask.rm.repository.common.domain.repository.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import timber.log.Timber

class CharactersRepositoryImpl(
    private val retrofit: Retrofit
) : CharactersRepository {
    override fun getAllCharacters(page: Int, name: String?): Flow<CharactersResponse> = flow{
        emit(Response.Loading)

        val response = retrofit
            .create(CharactersListApi::class.java)
            .getCharacters(page)
            .execute()

        if (response.code() == 404) {
            emit(Response.Success(emptyList()))
            return@flow
        }

        val responseData = response.body()

        if (responseData == null) {
            emit(Response.Error(
                UiText.StringResource(R.string.rm_api_error)
            ))
            Timber.e("Failed to fetch data from RM api. Message: ${response.errorBody().toString()}")
            return@flow
        }

        emit(Response.Success(responseData.results.toDomain()))
    }.flowOn(Dispatchers.IO)

    override fun getSelectedCharacters(ids: List<Int>): Flow<CharactersResponse> = flow {
        emit(Response.Loading)

        val response = retrofit
            .create(CharactersListApi::class.java)
            .getSelectedCharacters(ids)
            .execute()

        val responseData = response.body()

        if (responseData == null) {
            emit(Response.Error(
                UiText.StringResource(R.string.rm_api_error)
            ))
            Timber.e("Failed to fetch data from RM api. Message: ${response.errorBody().toString()}")
            return@flow
        }
        emit(Response.Success(responseData.toDomain()))
    }.flowOn(Dispatchers.IO)

    override fun getCharacter(id: Int): Flow<CharacterResponse> = flow {
        emit(Response.Loading)

        val response = retrofit
            .create(CharactersListApi::class.java)
            .getCharacter(id)
            .execute()

        val responseData = response.body()

        if (responseData == null) {
            emit(Response.Error(
                UiText.StringResource(R.string.rm_api_error)
            ))
            Timber.e("Failed to fetch data from RM api. Message: ${response.errorBody().toString()}")
            return@flow
        }
        emit(Response.Success(responseData.toDomain()))
    }.flowOn(Dispatchers.IO)
}