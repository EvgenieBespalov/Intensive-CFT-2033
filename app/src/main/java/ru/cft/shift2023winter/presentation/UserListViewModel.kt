package ru.cft.shift2023winter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.GetDataRandomUserUseCase

class UserListViewModel(
    private val getDataRandomUserUseCase: GetDataRandomUserUseCase,
) : ViewModel() {

    private val _state: MutableLiveData<UserListUiState> = MutableLiveData(UserListUiState.Initial)
    val state: LiveData<UserListUiState> = _state

    fun loadData(numberOfUsers: String, selectedGender: String?, seed: String?) {
        viewModelScope.launch {
            _state.value = UserListUiState.Loading


            try {
                val users = getDataRandomUserUseCase(numberOfUsers, selectedGender, seed)
                _state.value = users.let { UserListUiState.Content(it) }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = UserListUiState.Error(ex.message)
            }
        }
    }
}