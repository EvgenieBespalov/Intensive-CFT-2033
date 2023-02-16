package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.domain.repository.UserRepository

class SetToNeedNewUsersUseCase(private val repository: UserRepository) {
     operator fun invoke(isNeedToLoad: Boolean) {
        repository.setNeedToLoad(isNeedToLoad)
    }

}