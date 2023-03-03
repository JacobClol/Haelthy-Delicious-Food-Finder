package com.example.haelthydeliciousfoodfinder.data.datasources

import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import com.example.haelthydeliciousfoodfinder.data.models.SearchParams

interface RecipeRemoteDataSource {

    suspend fun getRecipeByQuery(params: SearchParams): ResponseSearchAPI
}