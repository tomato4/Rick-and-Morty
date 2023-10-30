package cz.ackee.testtask.rm.feature.list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.launch

class ListAllCharactersViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {
    private val _data = mutableStateOf(PaginationData<Character>(
        loadMore = this::loadMore
    ))
    val paginationDataState: State<PaginationData<Character>> get() = _data

    init {
        loadMore()
    }

    fun loadMore() {
        if (!_data.value.canPaginate) {
            return
        }

        _data.value = _data.value.getNextPaginationData()

        viewModelScope.launch {
            getAllCharactersUseCase(_data.value.page).collect {
                _data.value = when (it) {
                    is Response.Success -> _data.value.copy(
                        data = _data.value.data + it.data,
                        lastData = it,
                        endReached = it.data.isEmpty()
                    )
                    is Response.Loading, is Response.Error -> _data.value.copy(
                        lastData = it
                    )
                }
            }
        }
    }
}