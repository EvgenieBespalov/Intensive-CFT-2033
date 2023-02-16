package ru.cft.shift2023winter.domain.repository

import ru.cft.shift2023winter.data.model.UserModel
import ru.cft.shift2023winter.domain.entity.RandomUser
import ru.cft.shift2023winter.domain.entity.User

interface UserRepository {
    suspend fun getData(numberOfUsers: String, selectedGender: String?, seed: String?): List<User>

    fun getUserById(id: String): User

    fun setNeedToLoad(isNeedToLoad: Boolean)

}