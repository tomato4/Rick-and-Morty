package cz.ackee.testtask.rm.feature.favorite.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.domain.usecase.GetFavoriteCharactersUseCase
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase
) : ViewModel() {
    private val _paginationData = mutableStateOf(
        PaginationData<Character>(
            loadMore = this::onLoadMore
        )
    )
    val paginationData: State<PaginationData<Character>> get() = _paginationData

    fun onLoadMore() {
        if (!_paginationData.value.canPaginate) {
            return
        }

        _paginationData.value = _paginationData.value.getNextPaginationData()

        viewModelScope.launch {
            getFavoriteCharactersUseCase(_paginationData.value.page).collect {
                _paginationData.value = when (it) {
                    is Response.Success -> _paginationData.value.copy(
                        data = _paginationData.value.data + it.data,
                        lastData = it,
                        endReached = it.data.isEmpty()
                    )
                    is Response.Loading, is Response.Error -> _paginationData.value.copy(
                        lastData = it
                    )
                }
            }
        }
    }
}