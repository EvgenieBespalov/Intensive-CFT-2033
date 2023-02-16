package ru.cft.shift2023winter.domain.entity


data class User(
    val gender: String,
    val name: String,
    val location: Location,
    val email: String,
    val login: Login,
    val dateOfBirth: DateOfBirth,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val id: String,
    val picture: Picture,
    val nat: String
)