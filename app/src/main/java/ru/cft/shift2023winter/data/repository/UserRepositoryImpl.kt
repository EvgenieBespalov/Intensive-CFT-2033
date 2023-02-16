package ru.cft.shift2023winter.data.repository

import ru.cft.shift2023winter.data.api.RandomUserApi
import ru.cft.shift2023winter.data.converter.RandomUserConverter
import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.domain.repository.UserRepository

class UserRepositoryImpl(
    private val randomUserApi: RandomUserApi,
    private val converter: RandomUserConverter,
)  : UserRepository {

    private val users = mutableListOf<User>()
    private var isNeedToLoad = true

    override fun getUserById(id: String): User = users.first{ it -> it.id == id}

    override suspend fun getData(numberOfUsers: String, selectedGender: String?, seed: String?): List<User> {
        if(isNeedToLoad == true){
            isNeedToLoad = false
            users.clear()
            return randomUserApi.getData(numberOfUsers, selectedGender, seed).listUsers.map { converter.convertUser(it) }
                .also { users.addAll(it) }
        }
        isNeedToLoad = false
        return users
    }

    override fun setNeedToLoad(isNeedToLoad: Boolean) {
        this.isNeedToLoad = isNeedToLoad
    }

}