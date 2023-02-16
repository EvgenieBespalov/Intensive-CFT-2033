package ru.cft.shift2023winter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.GetUserByIdUseCase
import ru.cft.shift2023winter.domain.usecase.SetToNeedNewUsersUseCase

class FilterViewModel (
    private val setToNeedNewUsersUseCase: SetToNeedNewUsersUseCase
) : ViewModel() {

    private val _state: MutableLiveData<FilterUiState> = MutableLiveData(FilterUiState.Initial)
    val state: LiveData<FilterUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = FilterUiState.Initial
        }
    }

    fun setNeedToLoadNewUsers(){
        viewModelScope.launch {
            setToNeedNewUsersUseCase(true)
        }
    }

}