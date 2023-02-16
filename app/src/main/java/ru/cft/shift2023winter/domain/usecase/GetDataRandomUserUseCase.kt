package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.domain.repository.UserRepository

class GetDataRandomUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(numberOfUsers: String, selectedGender: String?, seed: String?): List<User> = repository.getData(numberOfUsers, selectedGender, seed)


}