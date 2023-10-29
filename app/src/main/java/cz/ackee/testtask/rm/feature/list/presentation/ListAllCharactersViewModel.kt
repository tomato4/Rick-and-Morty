package cz.ackee.testtask.rm.feature.list.presentation

import androidx.lifecycle.ViewModel
import cz.ackee.testtask.rm.repository.list.domain.usecase.GetAllCharactersUseCase

class ListAllCharactersViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

}