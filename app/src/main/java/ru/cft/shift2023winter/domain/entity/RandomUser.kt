package ru.cft.shift2023winter.domain.entity

data class RandomUser(
    val listUsers: ArrayList<User> = arrayListOf(),
    var info: Info
)