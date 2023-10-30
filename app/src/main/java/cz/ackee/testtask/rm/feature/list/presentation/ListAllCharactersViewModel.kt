package cz.ackee.testtask.rm.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import cz.ackee.testtask.rm.repository.common.model.Character

class ListAllCharactersViewModel(
    private val pager: Pager<Int, Character>
) : ViewModel() {
    val characterFlow = pager.flow.cachedIn(viewModelScope)
}