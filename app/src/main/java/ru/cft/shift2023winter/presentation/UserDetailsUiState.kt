package ru.cft.shift2023winter.presentation

import ru.cft.shift2023winter.domain.entity.User

sealed interface UserDetailsUiState{

    object Initial : UserDetailsUiState

    object Loading : UserDetailsUiState

    data class Content(val user: User) : UserDetailsUiState

    data class Error(val message: String?) : UserDetailsUiState
}
