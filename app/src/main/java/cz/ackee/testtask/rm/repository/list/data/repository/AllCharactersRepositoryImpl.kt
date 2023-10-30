package cz.ackee.testtask.rm.repository.list.data.repository

import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.app.common.UiText
import cz.ackee.testtask.rm.repository.list.data.mapper.toDomain
import cz.ackee.testtask.rm.repository.list.domain.repository.AllCharactersRepository
import cz.ackee.testtask.rm.repository.list.domain.repository.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import timber.log.Timber

class AllCharactersRepositoryImpl(
    private val retrofit: Retrofit
) : AllCharactersRepository {
    override fun getAllCharacters(page: Int): Flow<CharactersResponse> = flow{
        emit(Response.Loading)

        val response = retrofit
            .create(CharactersListApi::class.java)
            .getCharacters(page)
            .execute()
        val responseData = response.body()

        if (responseData == null) {
            emit(Response.Error(
                UiText.StringResource(R.string.rm_api_error)
            ))
            Timber.e("Failed to fetch data from RM api. Message: ${response.errorBody().toString()}")
            return@flow
        }

        emit(Response.Success(responseData.results.toDomain()))
    }
}