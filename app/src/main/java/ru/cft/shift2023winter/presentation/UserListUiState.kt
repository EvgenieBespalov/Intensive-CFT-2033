package ru.cft.shift2023winter.presentation

import ru.cft.shift2023winter.domain.entity.User

sealed interface UserListUiState{
    object Initial : UserListUiState

    object Loading : UserListUiState

    data class Content(val users: List<User>) : UserListUiState

    data class Error(val message: String?) : UserListUiState
}