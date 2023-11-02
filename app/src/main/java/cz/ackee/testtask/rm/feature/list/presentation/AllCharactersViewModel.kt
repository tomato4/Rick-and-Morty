package cz.ackee.testtask.rm.feature.list.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AllCharactersViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {
    private val _data = mutableStateOf(PaginationData<Character>(
        loadMore = this::onLoadMore
    ))
    val paginationDataState: State<PaginationData<Character>> get() = _data

    private val _nameSearch: MutableState<String?> = mutableStateOf(null)
    val nameSearch: State<String?> get() = _nameSearch

    private var _reloadJob: Job? = null

    fun onLoadMore() {
        if (!_data.value.canPaginate) {
            return
        }

        _data.value = _data.value.getNextPaginationData()

        viewModelScope.launch {
            getAllCharactersUseCase(_data.value.page, nameSearch.value).collect {
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

    fun onSearchNameChange(newName: String?) {
        _nameSearch.value = newName

        if (newName == null) {
            _data.value = PaginationData(
                loadMore = this::onLoadMore
            )
            onLoadMore()
            return
        }

        _reloadJob?.cancel()
        _reloadJob = CoroutineScope(Dispatchers.Default).launch {
            delay(500)

            _data.value = PaginationData(
                loadMore = this@AllCharactersViewModel::onLoadMore
            )

            if (newName.isNotEmpty()) {
                onLoadMore()
            }
        }
    }
}