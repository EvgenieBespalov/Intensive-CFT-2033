package ru.cft.shift2023winter.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.cft.shift2023winter.data.model.RandomUserModel

interface RandomUserApi {
    @GET("?")
    suspend fun getData(@Query("results") numberOfUsers: String, @Query("gender") selectedGender: String?, @Query("seed") seed: String?): RandomUserModel
}