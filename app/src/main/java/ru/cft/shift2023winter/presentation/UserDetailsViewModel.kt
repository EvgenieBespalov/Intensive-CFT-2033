package ru.cft.shift2023winter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.GetUserByIdUseCase

class UserDetailsViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val userIdName: String
) : ViewModel() {

    private val _state: MutableLiveData<UserDetailsUiState> = MutableLiveData()
    val state: LiveData<UserDetailsUiState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = UserDetailsUiState.Loading

            try {
                val user = getUserByIdUseCase(userIdName)
                _state.value = user.let { UserDetailsUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = UserDetailsUiState.Error(ex.message)
            }
        }
    }

}