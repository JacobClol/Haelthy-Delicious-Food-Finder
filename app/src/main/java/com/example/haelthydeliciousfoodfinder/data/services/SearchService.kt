package com.example.haelthydeliciousfoodfinder.data.services

import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("v2")
    suspend fun getItems(
        @Query("type") name: String,
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("diet") diet: String,
        @Query("health") health: String
    ): ResponseSearchAPI
}