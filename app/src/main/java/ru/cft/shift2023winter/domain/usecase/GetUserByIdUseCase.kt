package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.domain.repository.UserRepository

class GetUserByIdUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(userIdName: String): User =
        repository.getUserById(userIdName)
}