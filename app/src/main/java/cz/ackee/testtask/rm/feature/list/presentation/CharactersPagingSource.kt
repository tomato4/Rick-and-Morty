package cz.ackee.testtask.rm.feature.list.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.flow.lastOrNull

class CharactersPagingSource(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1

        val data = getAllCharactersUseCase(page).lastOrNull()

        return when (data) {
            is Response.Success -> LoadResult.Page(
                data.data,
                prevKey = page - 1,
                nextKey = page + 1
            )
            is Response.Error -> LoadResult.Error(Throwable())
            is Response.Loading, null -> LoadResult.Error(Throwable())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}