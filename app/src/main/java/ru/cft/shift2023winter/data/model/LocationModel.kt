package ru.cft.shift2023winter.data.model

data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesModel,
    val timezone: TimezoneModel
)