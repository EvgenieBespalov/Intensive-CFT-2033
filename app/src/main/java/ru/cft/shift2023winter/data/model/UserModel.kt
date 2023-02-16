package ru.cft.shift2023winter.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val gender: String,
    val name: NameModel,
    val location: LocationModel,
    val email: String,
    val login: LoginModel,
    @field:SerializedName("dob")
    val dateOfBirth: DateOfBirthModel,
    val registered: RegisteredModel,
    val phone: String,
    val cell: String,
    val id: IdModel,
    val picture: PictureModel,
    val nat: String
)