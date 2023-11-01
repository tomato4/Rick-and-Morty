package cz.ackee.testtask.rm.feature.detail.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.app.common.UiText
import cz.ackee.testtask.rm.repository.common.domain.repository.CharacterResponse
import cz.ackee.testtask.rm.repository.detail.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val characterId: Int?
) : ViewModel() {
    private val _characterResponseState: MutableState<CharacterResponse> =
        mutableStateOf(Response.Loading)
    val characterResponseState: State<CharacterResponse> get() = _characterResponseState

    fun onLoadData() {
        if (characterId == null) {
            _characterResponseState.value = Response.Error(
                UiText.StringResource(R.string.error)
            )
            return
        }

        viewModelScope.launch {
            getCharacterDetailUseCase(characterId).collect {
                _characterResponseState.value = it
            }
        }
    }
}